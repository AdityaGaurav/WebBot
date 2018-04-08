package framework.customwaits;

import framework.Defaults;
import framework.conditioncheck.PreConditionCheck;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class CustomFluentWait<T> {

    private T input;
    private long timeOut;
    private long pollingTime;

    /**
     * @param input The input value to pass to the evaluated conditions.
     */
    public CustomFluentWait(T input) {
        PreConditionCheck.checkNotNull(input,"");
        this.input = input;
        this.timeOut = Defaults.DEFAULT_TIME_OUT;
        this.pollingTime = Defaults.DEFAULT_POLLING_TIME;
    }

    public CustomFluentWait(T input, long timeOut, long pollingTime) {
        this.input = input;
        this.timeOut = timeOut;
        this.pollingTime = pollingTime;
    }

    /**
     * This is a custom fluent wait. To use this method, a expected condition should be
     * wrapped in {@link Function}
     *
     * @param function Condition wrapped in {@link Function} instance
     * @return The function's return value if the function returned something different
     * from null or false before the timeout expired.
     * @throws TimeoutException If the timeout expires.
     */
    public <F> F waitForTheElementAsPerTheGivenCondition(Function<T, F> function) {
        Wait<T> wait = new FluentWait<>(input).
                withTimeout(this.timeOut, TimeUnit.SECONDS).
                pollingEvery(this.pollingTime, TimeUnit.MILLISECONDS).
                ignoring(NoSuchElementException.class).
                ignoring(StaleElementReferenceException.class).
                withMessage("Not able to find expected element. Timeout is set to " + timeOut+" seconds. ");
        return wait.until(function);
    }

    public <F> F waitForTheElementAsPerTheGivenCondition(List<Class<? extends Throwable>> listOfErrorToIgnore,
                                                         Function<T, F> function,String messageToDisplayAfterTimeOut) {
        Wait<T> wait = new FluentWait<>(input).
                withTimeout(this.timeOut, TimeUnit.SECONDS).
                pollingEvery(this.pollingTime, TimeUnit.MILLISECONDS).
                ignoreAll(listOfErrorToIgnore).
                withMessage(messageToDisplayAfterTimeOut+" The timeout is set to" + timeOut+" seconds. ");
        return wait.until(function);
    }

    public <F> F waitForAllElementAsPerTheGivenCondition(Function<T, F> function) {
        Wait<T> wait = new FluentWait<>(input).
                withTimeout(this.timeOut, TimeUnit.SECONDS).
                pollingEvery(this.pollingTime, TimeUnit.MILLISECONDS).
                ignoring(NoSuchElementException.class).
                ignoring(StaleElementReferenceException.class).
                withMessage("Not able to find expected element. Timeout is set to " + timeOut+" seconds. ");
        return wait.until(function);
    }


    public long getTimeOut() {
        return this.timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public long getPollingTime() {
        return pollingTime;
    }

    public void setPollingTime(long pollingTime) {
        this.pollingTime = pollingTime;
    }

    @Override
    public String toString() {
        return "CustomFluentWait{" +
                ", timeOut=" + timeOut +
                ", pollingTime=" + pollingTime +
                '}';
    }
}
