package fr.ekito.coroutines

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import kotlin.system.measureTimeMillis


class CoroutinesTest {

    @Test
    fun testCoroutines() = runBlocking {
        val main = measureTimeMillis {
            val jobs = List(ThreadsTest.MAX_THREADS) {
                launch(CommonPool) {
                    delay(ThreadsTest.WAIT)
                    print("[$it]")
                }
            }
            jobs.forEach { it.join() }
        }
        println("\ndone in $main")
    }
}