package com.gg.pojo;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Alan
 * @Description
 * @date 2023.08.07 18:54
 */
public class MyRequest implements Serializable {
    private static final long serialVersionUID = -8683434780092658943L;

    private String reqId;

    private String className;

    private String methodName;

    private Class<?>[] parameterTypes;


    private Object[] parameters;

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "MyRequest{" +
                "reqId='" + reqId + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }
}
