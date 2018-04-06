package ademar.study.template.core.interactor

import ademar.study.template.core.repository.HelloWorldRepository
import ademar.study.template.core.test.BaseTest
import ademar.study.template.core.test.Fixture
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class GetHelloWorldsTest : BaseTest() {

    @Mock lateinit var mockHelloWorldRepository: HelloWorldRepository

    @Test
    fun testExecute_success() {
        val useCase = GetHelloWorlds(mockHelloWorldRepository)
        val mockHello = Fixture.helloWorld()

        whenever(mockHelloWorldRepository.getAllHelloWorld()).thenReturn(Observable.just(listOf(mockHello)))

        useCase.execute()
                .test()
                .assertResult(mockHello)
                .assertNoErrors()

        verify(mockHelloWorldRepository).getAllHelloWorld()
        verifyNoMoreInteractions(mockHelloWorldRepository)
    }

    @Test
    fun testExecute_error() {
        val useCase = GetHelloWorlds(mockHelloWorldRepository)
        val mockHelloWorldError = Fixture.error()

        whenever(mockHelloWorldRepository.getAllHelloWorld()).thenReturn(Observable.error(mockHelloWorldError))

        useCase.execute()
                .test()
                .assertError(mockHelloWorldError)

        verify(mockHelloWorldRepository).getAllHelloWorld()
        verifyNoMoreInteractions(mockHelloWorldRepository)
    }

}
