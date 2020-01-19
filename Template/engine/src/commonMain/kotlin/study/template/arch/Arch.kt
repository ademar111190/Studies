package study.template.arch

/**
 * 1- An *User* demonstrate an *Intention* to the *Interactor*
 * 2- The *Interactor* retrieves from *Storage* the current *State*
 * 3- In possession of both *State* and *Intention* the *Interactor* triggers an *UseCase*
 * 4- The *UseCase* gives a *Result*
 * 5- The *Reducer* uses both *Result* and *State* to generate a new *State*
 * 6- The *Storage* saves the new *State*
 * 7- The *Presenter* maps the *State* to a *Model*
 * 8- The *View* renders the *Model*
 * 9- At any moment a *SideEffect* can happens
 */
interface Arch {

    /**
     * Should be implemented by a sealed class
     */
    interface Intention

    /**
     * Should be implemented by a class
     */
    interface Interactor

    /**
     * Should be implemented by a class
     */
    interface Storage

    /**
     * Should be implemented by a data class
     */
    interface State

    /**
     * Should be implemented by a function
     */
    interface UseCase

    /**
     * Should be implemented by a sealed class
     */
    interface Result

    /**
     * Should be implemented by a class
     */
    interface Reducer

    /**
     * Should be implemented by a function
     */
    interface Presenter

    /**
     * Should be implemented by a data class
     */
    interface Model

    /**
     * Should be implemented following the rule of the platform
     */
    interface View

    /**
     * Should be implemented by a function
     */
    interface SideEffect

}
