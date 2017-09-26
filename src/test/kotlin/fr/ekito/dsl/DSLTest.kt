package fr.ekito.dsl

import org.junit.Assert
import org.junit.Test


class MyServiceA
class MyServiceB(val serviceA: MyServiceA)
class MyServiceC(val serviceA: MyServiceA, val serviceB: MyServiceB)

class SimpleModule : Module() {
    override fun context() = declareContext {
        provide { MyServiceA() }
        provide { MyServiceB(get()) }
        provide { MyServiceC(get(), get()) }
    }
}

class DSLTestTest {
    @Test
    fun testDSL() {
        val ctx = CoreContext()
        ctx.build(SimpleModule())
        val serviceB = ctx.inject<MyServiceB>()
        val serviceC = ctx.inject<MyServiceC>()
        val serviceA = ctx.inject<MyServiceA>()

        Assert.assertNotNull(serviceB)
        Assert.assertNotNull(serviceB.serviceA)

        Assert.assertEquals(serviceA, serviceB.serviceA)
        Assert.assertEquals(serviceC.serviceA, serviceB.serviceA)
        Assert.assertEquals(serviceC.serviceB, serviceB)

        // Check instances
        Assert.assertEquals(3, ctx.instances.size)
        Assert.assertEquals(3, ctx.definitions.size)
    }
}