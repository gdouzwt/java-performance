/*
 * [The "BSD license"] Copyright (c) 2011-2019 闲大赋 (李家智) All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer. 2. Redistributions in
 * binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other
 * materials provided with the distribution. 3. The name of the author may not
 * be used to endorse or promote products derived from this software without
 * specific prior written permission. THIS SOFTWARE IS PROVIDED BY THE AUTHOR
 * ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.beetl.core.exception;

/**
 * Beetl异常基类，此异常包含了错误码，错误符号，所属的模板文件，以及错误详情，错误栈
 * 
 * @author joelli
 *
 */
public class BeetlException extends RuntimeException {
    private static final long serialVersionUID = -3710295933498411101L;
    public static final String ERROR = "ERROR";
    /**
     * 引用属性错误
     */
    public static final String ATTRIBUTE_INVALID = "ATTRIBUTE_INVALID";
    public static final String ATTRIBUTE_NOT_FOUND = "ATTRIBUTE_NOT_FOUND";
    /**
     * 错误码
     */
    private String detailCode;

    public BeetlException(String detailCode, String msg) {
        super(msg);
        this.detailCode = detailCode;

    }

    public BeetlException(String detailCode) {
        super();
        this.detailCode = detailCode;
    }

    public BeetlException(String detailCode, Throwable cause) {
        super(cause);
        this.detailCode = detailCode;

    }

    public BeetlException(String detailCode, String msg, Throwable cause) {
        super(msg, cause);
        this.detailCode = detailCode;
    }

    @Override
    public String toString() {
        return detailCode + super.getMessage();
    }

}
