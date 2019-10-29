package demo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

 

@Aspect
@Component
public class CRMLoggingAspect {
	
	//set up logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	//set up poincut delaration
	@Pointcut("execution(* demo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	//set up poincut delaration for service package
	@Pointcut("execution(* demo.service.*.*(..))")
	private void forServicePackage() {}
	
	//set up poincut delaration for dao package
	@Pointcut("execution(* demo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	
	//add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		//display method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n @Befoe : calling method "+theMethod);
		
		//get args
		Object[] args = theJoinPoint.getArgs();
		for(Object o:args) {
			myLogger.info("\n--- arguments "+o);
		}
	}
	
	//add @AfterReturning advice
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="theResult"
			)
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		//display method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n @AfterReturning : calling method "+theMethod);
		
		//display data returned
		myLogger.info("\nResult " + theResult);
		
		
	}
	
}
