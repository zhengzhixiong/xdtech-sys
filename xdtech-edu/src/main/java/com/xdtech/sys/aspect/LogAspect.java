package com.xdtech.sys.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xdtech.common.utils.JsonUtil;
import com.xdtech.common.utils.RequestUtil;
import com.xdtech.sys.model.Log;
import com.xdtech.sys.model.User;
import com.xdtech.sys.service.LogService;
import com.xdtech.sys.util.SessionContextUtil;

/**
 * 日志记录，添加、删除、修改方法AOP
 * 
 * @author HotStrong
 * 
 */
@Aspect
@Component
public class LogAspect {

	@Autowired
	private LogService logService;// 日志记录Service
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory
			.getLogger(LogAspect.class);

	// Service层切点
	@Pointcut("@annotation(com.xdtech.sys.aspect.SystemServiceLog)")
	public void serviceAspect() {

	}

	// Controller层切点
	@Pointcut("@annotation(com.xdtech.sys.aspect.SystemControllerLog)")
	public void controllerAspect() {

	}

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 *            切点
	 */
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		Class<?> cl = joinPoint.getTarget().getClass();
		String className = cl.getName();
		String methodName = joinPoint.getSignature().getName();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		// 读取session中的用户
		User currentUser = SessionContextUtil.getCurrentUser();
		// 请求的IP
		String ip = request.getRemoteAddr();
		try {
			// *========控制台输出=========*//
//			System.out.println("=====前置通知开始=====");
//			System.out.println("请求方法:"
//					+ (joinPoint.getTarget().getClass().getName() + "."
//							+ joinPoint.getSignature().getName() + "()"));
//			System.out.println("方法描述:"+ getControllerMethodDescription(joinPoint));
//			System.out.println("请求人:" + currentUser.getName());
//			System.out.println("请求IP:" + ip);
			// *========数据库日志=========*//
			Log log = new Log();
			log.setOperateComment(getControllerMethodDescription(joinPoint));
			log.setMethod(className + "." + methodName + "()");
			// log.setType("0");
			// log.setExceptionCode(null);
			// log.setExceptionDetail(null);
			// log.setParams(null);
			// log.setCreateBy(user);
			log.setIp(RequestUtil.getClientIp(request));
			if (currentUser == null) {
				log.setOperator(request.getRemoteUser());
			} else {
				log.setOperator(currentUser.getLoginName());
			}
			log.setOperateTime(new Date());
			// 保存数据库
			logService.save(log);
//			System.out.println("=====前置通知结束=====");
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==前置通知异常==");
			logger.error("异常信息:{}", e);
		}
	}

	/**
	 * 异常通知 用于拦截service层记录异常日志
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		Class<?> cl = joinPoint.getTarget().getClass();
		String className = cl.getName();
		String methodName = joinPoint.getSignature().getName();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		// 读取session中的用户
		User currentUser = SessionContextUtil.getCurrentUser();
		// 获取请求ip
		String ip = request.getRemoteAddr();
		// 获取用户请求方法的参数并序列化为JSON格式字符串
		String params = "";
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				params += JsonUtil.getJsonStr(joinPoint.getArgs()[i]) + ";";
			}
		}
		try {
			/* ========控制台输出========= */
			System.out.println("=====异常通知开始=====");
			System.out.println("异常代码:" + e.getClass().getName());
			System.out.println("异常信息:" + e.getMessage());
			System.out.println("异常方法:"
					+ (joinPoint.getTarget().getClass().getName() + "."
							+ joinPoint.getSignature().getName() + "()"));
			System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));
//			System.out.println("请求人:" + currentUser.getLoginName());
			System.out.println("请求IP:" + ip);
			System.out.println("请求参数:" + params);
			/* ==========数据库日志========= */
			Log log = new Log();
			log.setOperateComment(getControllerMethodDescription(joinPoint));
			log.setMethod(className + "." + methodName + "()");
			// log.setType("0");
			// log.setExceptionCode(null);
			// log.setExceptionDetail(null);
			// log.setParams(null);
			// log.setCreateBy(user);
			log.setIp(RequestUtil.getClientIp(request));
			if (currentUser == null) {
				log.setOperator(request.getRemoteUser());
			} else {
				log.setOperator(currentUser.getLoginName());
			}
			log.setOperateTime(new Date());
			// 保存数据库
			logService.save(log);
			System.out.println("=====异常通知结束=====");
		} catch (Exception ex) {
			// 记录本地异常日志
			logger.error("==异常通知异常==");
			logger.error("异常信息:{}", ex);
		}
		/* ==========记录本地异常日志========== */
		logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget()
				.getClass().getName()
				+ joinPoint.getSignature().getName(), e.getClass().getName(),
				e.getMessage(), params);

	}

	/**
	 * 获取注解中对方法的描述信息 用于service层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getServiceMthodDescription(JoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemServiceLog.class)
							.description();
					break;
				}
			}
		}
		return description;
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getControllerMethodDescription(JoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(
							SystemControllerLog.class).description();
					break;
				}
			}
		}
		return description;
	}

	/************************* 旧方式 ****************/

	/**
	 * 添加业务逻辑方法切入点
	 */
	// @Pointcut("execution(* com.xxx.service.*.insert*(..))")
	public void insertServiceCall() {
	}

	/**
	 * 修改业务逻辑方法切入点
	 */
	// @Pointcut("execution(* com.xxx.service.*.update*(..))")
	// @Pointcut("execution(* com.edu.sys.service.impl.*.*(..))")
	public void updateServiceCall() {

	}

	/**
	 * 删除影片业务逻辑方法切入点
	 */
	// @Pointcut("execution(* com.xxx.service.FilmService.deleteFilm(..))")
	public void deleteFilmCall() {
	}

	// @Pointcut("execution(* com.xdtech.**.service.impl.*.*(..))")
	// public void serviceSavePointcut() {
	//
	// }

	// http://itindex.net/detail/50710-springaop-controller-service
	// @Around("execution(* com.xdtech.**.controller.*.*(..))")
	// @AfterReturning(argNames="rtv",returning="rvt",
	// pointcut="execution(* com.edu.sys.service.impl.*.*(..))")
	public Object processLog(ProceedingJoinPoint joinPoint)
			throws java.lang.Throwable {
		// String methodInfo = joinPoint.toString();
		Class<?> cl = joinPoint.getTarget().getClass();
		String className = cl.getName();
		String methodName = joinPoint.getSignature().getName();
		System.out.println("执行前" + className + "--------" + methodName);
		// System.out.println("执行目标方法之前，模拟开始事务 ...");
		// 执行目标方法，并保存目标方法执行后的返回值
		Object rvt = joinPoint.proceed();
		System.out.println("执行后" + className + "--------" + methodName);
		// if (!className.contains("LogService")) {
		// if
		// (methodName.contains("save")||methodName.contains("update")||methodName.contains("delete")||methodName.contains("load"))
		// {
		// //只记录增删改查日志
		// Log log = new Log();
		// User currentUser = SessionContextUtil.getCurrentUser();
		// HttpServletRequest request =
		// ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		// log.setIp(RequestUtil.getClientIp(request));
		// if (currentUser==null) {
		// log.setOperator(request.getRemoteUser());
		// }else {
		// log.setOperator(currentUser.getLoginName());
		// }
		// log.setOperateTime(new Date());
		// log.setMethod(className+"#"+methodName);
		// logService.save(log);
		// }
		// }
		// System.out.println("执行目标方法之后，模拟结束事务 ...");
		return rvt;

	}

	/**
	 * 管理员添加操作日志(后置通知)
	 * 
	 * @param joinPoint
	 * @param rtv
	 * @throws Throwable
	 */
	// @AfterReturning(value="insertServiceCall()", argNames="rtv",
	// returning="rtv")
	public void insertServiceCallCalls(JoinPoint joinPoint, Object rtv)
			throws Throwable {

		// 获取登录管理员id
		// Long adminUserId = logService.loginUserId();
		//
		// if(adminUserId == null){//没有管理员登录
		// return;
		// }
		//
		// //判断参数
		// if(joinPoint.getArgs() == null){//没有参数
		// return;
		// }
		//
		// //获取方法名
		// String methodName = joinPoint.getSignature().getName();
		//
		// //获取操作内容
		// String opContent = adminOptionContent(joinPoint.getArgs(),
		// methodName);
		//
		// 创建日志对象
		// Log log = new Log();
		// log.setUserid(logService.loginUserId());//设置管理员id
		// log.setCreatedate(new Date());//操作时间
		// log.setContent(opContent);//操作内容
		// log.setOperation("添加");//操作
		//
		// logService.log(log);//添加日志
	}

	/**
	 * 前置通知
	 */
	// @Before("updateServiceCall()")
	// public void logbefore(){
	// System.out.println("前置通知：方法即将被执行！");
	// }
	/**
	 * 管理员修改操作日志(后置通知)
	 * 
	 * @param joinPoint
	 * @param rtv
	 * @throws Throwable
	 */
	// @AfterReturning(value="updateServiceCall()", argNames="rtv",
	// returning="rtv")
	public void updateServiceCallCalls(JoinPoint joinPoint, Object rtv)
			throws Throwable {

		// 获取登录管理员id
		// Long adminUserId = logService.loginUserId();
		//
		// if(adminUserId == null){//没有管理员登录
		// return;
		// }
		//
		// //判断参数
		// if(joinPoint.getArgs() == null){//没有参数
		// return;
		// }
		//
		// //获取方法名
		// String methodName = joinPoint.getSignature().getName();
		//
		// //获取操作内容
		// String opContent = adminOptionContent(joinPoint.getArgs(),
		// methodName);
		//
		// //创建日志对象
		// Log log = new Log();
		// log.setUserid(logService.loginUserId());//设置管理员id
		// log.setCreatedate(new Date());//操作时间
		// log.setContent(opContent);//操作内容
		// log.setOperation("修改");//操作
		//
		// logService.log(log);//添加日志
	}

	/**
	 * 管理员删除影片操作(环绕通知)，使用环绕通知的目的是 在影片被删除前可以先查询出影片信息用于日志记录
	 * 
	 * @param joinPoint
	 * @param rtv
	 * @throws Throwable
	 */
	// @Around(value="deleteFilmCall()", argNames="rtv")
	public Object deleteFilmCallCalls(ProceedingJoinPoint pjp) throws Throwable {

		Object result = null;
		// 环绕通知处理方法
		try {

			// //获取方法参数(被删除的影片id)
			// Integer id = (Integer)pjp.getArgs()[0];
			// Film obj = null;//影片对象
			// if(id != null){
			// //删除前先查询出影片对象
			// obj = filmService.getFilmById(id);
			// }
			//
			// //执行删除影片操作
			// result = pjp.proceed();
			//
			// if(obj != null){
			//
			// //创建日志对象
			// Log log = new Log();
			// log.setUserid(logService.loginUserId());//用户编号
			// log.setCreatedate(new Date());//操作时间
			//
			// StringBuffer msg = new StringBuffer("影片名 : ");
			// msg.append(obj.getFname());
			// log.setContent(msg.toString());//操作内容
			//
			// log.setOperation("删除");//操作
			//
			// logService.log(log);//添加日志
			// }

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * 使用Java反射来获取被拦截方法(insert、update)的参数值， 将参数值拼接为操作内容
	 */
	public String adminOptionContent(Object[] args, String mName)
			throws Exception {

		if (args == null) {
			return null;
		}

		StringBuffer rs = new StringBuffer();
		rs.append(mName);
		String className = null;
		int index = 1;
		// 遍历参数对象
		for (Object info : args) {

			// 获取对象类型
			className = info.getClass().getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			rs.append("[参数" + index + "，类型：" + className + "，值：");

			// 获取对象的所有方法
			Method[] methods = info.getClass().getDeclaredMethods();

			// 遍历方法，判断get方法
			for (Method method : methods) {

				String methodName = method.getName();
				// 判断是不是get方法
				if (methodName.indexOf("get") == -1) {// 不是get方法
					continue;// 不处理
				}

				Object rsValue = null;
				try {

					// 调用get方法，获取返回值
					rsValue = method.invoke(info);

					if (rsValue == null) {// 没有返回值
						continue;
					}

				} catch (Exception e) {
					continue;
				}

				// 将值加入内容中
				rs.append("(" + methodName + " : " + rsValue + ")");
			}

			rs.append("]");

			index++;
		}

		return rs.toString();
	}

}