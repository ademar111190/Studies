package ademar.study.reddit.core.interactor

import ademar.study.reddit.core.repository.HelloWorldRepository
import ademar.study.reddit.core.test.BaseTest
import ademar.study.reddit.core.test.Fixture
import io.reactivex.Observable
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as whenever

class GetHelloWorldUseCaseTest : BaseTest() {

    @Mock lateinit var mockHelloWorldRepository: HelloWorldRepository

    @Test
    fun testExecute_success() {
        val useCase = GetHelloWorldUseCase(mockHelloWorldRepository)
        val mockHelloWorld = Fixture.helloWorld.makeModel()

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
        val useCase = GetHelloWorldUseCase(mockHelloWorldRepository)
        val mockHelloWorldError = Fixture.error.makeModel()

        whenever(mockHelloWorldRepository.getHelloWorld()).thenReturn(Observable.error(mockHelloWorldError))

        useCase.execute()
                .test()
                .assertError(mockHelloWorldError)

        verify(mockHelloWorldRepository).getHelloWorld()
        verifyNoMoreInteractions(mockHelloWorldRepository)
    }

}
