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

class GetHelloWorldTest : BaseTest() {

    @Mock lateinit var mockHelloWorldRepository: HelloWorldRepository

    @Test
    fun testExecute_success() {
        val useCase = GetHelloWorld(mockHelloWorldRepository)
        val mockHelloWorld = Fixture.helloWorld()

        whenever(mockHelloWorldRepository.getHelloWorld()).thenReturn(Observable.just(mockHelloWorld))

        useCase.execute()
                .test()
                .assertResult(mockHelloWorld)
                .assertNoErrors()

        verify(mockHelloWorldRepository).getHelloWorld()
        verifyNoMoreInteractions(mockHelloWorldRepository)
    }

    @Test
    fun testExecute_error() {
        val useCase = GetHelloWorld(mockHelloWorldRepository)
        val mockHelloWorldError = Fixture.error()

        whenever(mockHelloWorldRepository.getHelloWorld()).thenReturn(Observable.error(mockHelloWorldError))

        useCase.execute()
                .test()
                .assertError(mockHelloWorldError)

        verify(mockHelloWorldRepository).getHelloWorld()
        verifyNoMoreInteractions(mockHelloWorldRepository)
    }

}
