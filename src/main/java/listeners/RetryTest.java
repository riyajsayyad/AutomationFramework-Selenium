package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer{

	int cnt = 0;
	int maxTry = 1;
	@Override
	public boolean retry(ITestResult result) {
	
		if (cnt < maxTry) {
			cnt++;
			return true;
		}
		return false;
	}

}
