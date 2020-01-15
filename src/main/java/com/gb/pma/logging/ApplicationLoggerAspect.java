package com.gb.pma.logging;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("within(com.gb.pma.controller..*)" + "|| within(com.gb.pma.dao..*)")
	public void definePackagePointCuts() {
		// methode vide juste pour nommer la localisation specifique du pointcut
	}

	@Around("definePackagePointCuts()")
	public Object logAround(ProceedingJoinPoint jp) {
		this.log.debug("\n \n \n \n");
		this.log.debug("************** Before methode execution *********** \n {}.{} () with arguments[s] = {}",
				jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		this.log.debug("______________________________________________ \n \n \n");

		Object o = null;
		try {
			o = jp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.log.debug("\n \n \n \n");
		this.log.debug("************** After methode execution *********** \n {}.{} () with arguments[s] = {}",
				jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		this.log.debug("______________________________________________ \n \n \n");

		return o;
	}
}
