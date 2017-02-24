package ademar.study.test.core.interactor

import ademar.study.test.core.repository.HelloWorldRepository
import ademar.study.test.core.test.BaseTest
import ademar.study.test.core.test.Fixture
import io.reactivex.Observable
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as whenever

class GetAllHelloWorldUseCaseTest : BaseTest() {

    @Mock lateinit var mockHelloWorldRepository: HelloWorldRepository

    @Test
    fun testExecute_success() {
        val useCase = GetAllHelloWorldUseCase(mockHelloWorldRepository)
        val mockHello = Fixture.helloWorld.makeModel()

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
        val useCase = GetAllHelloWorldUseCase(mockHelloWorldRepository)
        val mockHelloWorldError = Fixture.error.makeModel()

        whenever(mockHelloWorldRepository.getAllHelloWorld()).thenReturn(Observable.error(mockHelloWorldError))

        useCase.execute()
                .test()
                .assertError(mockHelloWorldError)

        verify(mockHelloWorldRepository).getAllHelloWorld()
        verifyNoMoreInteractions(mockHelloWorldRepository)
    }

}
