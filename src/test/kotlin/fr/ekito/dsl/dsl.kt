package fr.ekito.dsl

import kotlin.reflect.KClass

data class BeanDefinition<T>(val definition: () -> T, val name: String, val clazz: KClass<*>)

class Context(val coreContext: CoreContext) {

    var definitions = listOf<BeanDefinition<*>>()

    inline fun <reified T> provide(noinline definition: () -> T) {
        val clazz = T::class
        val name = clazz.java.simpleName
        definitions += BeanDefinition(definition, name, clazz)
    }

    inline fun <reified T> get(): T = coreContext.inject()
}

abstract class Module() {

    lateinit var coreContext: CoreContext

    abstract fun context(): Context

    fun declareContext(init: Context.() -> Unit) = Context(coreContext).apply(init)
}

class CoreContext {

    var instances = HashMap<KClass<*>, Any>()
    var definitions = listOf<BeanDefinition<*>>()

    fun <T : Module> build(module: T) {
        // bind context
        module.coreContext = this
        // load definitions
        definitions += module.context().definitions
    }

    inline fun <reified T> inject(): T {
        val clazz = T::class

        // found one ?
        val foundInstance: T? = instances[clazz] as? T

        // create one ?
        val createdInstance: T? = if (foundInstance == null) {
            definitions.firstOrNull { it.clazz == clazz }?.let { it.definition.invoke() as? T? }
        } else null

        // Got it ?
        val instance: T = (foundInstance ?: createdInstance) ?: error("Bean $clazz not found")

        // Save it
        if (createdInstance != null && foundInstance == null) {
            instances[clazz] = createdInstance as Any
        }
        return instance
    }
}