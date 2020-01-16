package com.github.hollykunge.security.data.exception;

import com.github.hollykunge.security.common.exception.BaseException;
import com.github.hollykunge.security.data.dictionary.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * task业务用异常
 */
@Slf4j
public class TaskBizException extends BaseException {

	private static final long serialVersionUID = -6552248511084911254L;

	public TaskBizException() {
	}


	/**
	 * Instantiates a new task exception.
	 *
	 * @param code the code
	 * @param msg  the msg
	 */
	public TaskBizException(int code, String msg) {
		super(msg,code);
		log.error("<== TaskBizException, code:{}, message:{}", this.getStatus(), super.getMessage());
	}

	public TaskBizException(ErrorCodeEnum codeEnum) {
		super(codeEnum.msg(), codeEnum.code());
		log.error("<== TaskBizException, code:{}, message:{}", this.getStatus(), super.getMessage());
	}


}
