package com.litesuits.http.request.param;

import com.litesuits.http.listener.HttpListener;
import com.litesuits.http.request.content.HttpBody;
import com.litesuits.http.request.content.StringBody;
import com.litesuits.http.request.content.UrlEncodedFormBody;
import com.litesuits.http.request.content.multi.MultipartBody;
import com.litesuits.http.request.query.ModelQueryBuilder;

import java.util.LinkedHashMap;

/**
 * mark a class as a http parameter modle.
 * classes that implement this will be parsed to http parameter.
 * 杂袍1只， 墨兰1对， 大红1对， 红鼻10条，金光10条，玻璃扯旗10条，三角10条，红绿灯10条。
 *
 * @author MaTianyu
 *         2014-1-19上午2:39:31
 */
public abstract class HttpRichParamModel<T> implements HttpParamModel {
    @NonHttpParam
    protected HttpListener<T> httpListener;
    @NonHttpParam
    protected LinkedHashMap<String, String> headers;
    @NonHttpParam
    protected ModelQueryBuilder modelQueryBuilder;
    @NonHttpParam
    protected HttpBody httpBody;
    @NonHttpParam
    protected boolean attachToUrl = true;
    @NonHttpParam
    protected String uri;

    public void reset() {
        httpListener = null;
        headers = null;
        modelQueryBuilder = null;
        httpBody = null;
        uri = null;
        attachToUrl = true;
    }

    public final boolean isAttachToUrl() {
        return attachToUrl;
    }

    @SuppressWarnings("unchecked")
    public final <H extends HttpRichParamModel<T>> H setAttachToUrl(boolean attachToUrl) {
        this.attachToUrl = attachToUrl;
        return (H) this;
    }

    public final HttpListener<T> getHttpListener() {
        if (httpListener == null) {
            httpListener = createHttpListener();
        }
        return httpListener;
    }

    @SuppressWarnings("unchecked")
    public final <H extends HttpRichParamModel<T>> H setHttpListener(HttpListener<T> httpListener) {
        this.httpListener = httpListener;
        return (H) this;
    }

    public final LinkedHashMap<String, String> getHeaders() {
        if (headers == null) {
            headers = createHeaders();
        }
        return headers;
    }

    public String getUri() {
        if (uri == null) {
            uri = createHttpUri();
        }
        return uri;
    }

    public <H extends HttpRichParamModel<T>> H setUri(String uri) {
        this.uri = uri;
        return (H) this;
    }

    public final HttpRichParamModel setHeaders(LinkedHashMap<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public final ModelQueryBuilder getModelQueryBuilder() {
        if (modelQueryBuilder == null) {
            modelQueryBuilder = createQueryBuilder();
        }
        return modelQueryBuilder;
    }

    public final HttpRichParamModel setModelQueryBuilder(ModelQueryBuilder modelQueryBuilder) {
        this.modelQueryBuilder = modelQueryBuilder;
        return this;
    }

    public final HttpBody getHttpBody() {
        if (httpBody == null) {
            httpBody = createHttpBody();
        }
        return httpBody;
    }

    public final HttpRichParamModel setHttpBody(HttpBody httpBody) {
        this.httpBody = httpBody;
        return this;
    }

    /**
     * craete headers for request.
     */
    protected LinkedHashMap<String, String> createHeaders() {return null;}

    /**
     * create http uri for request.
     */
    protected String createHttpUri() {return null;}


    /**
     * create parameter builder.
     */
    protected ModelQueryBuilder createQueryBuilder() {return null;}

    /**
     * create http listener for request.
     */
    protected HttpListener<T> createHttpListener() {return null;}

    /**
     * create http body for POST/PUT... request.
     *
     * @return such as {@link StringBody}, {@link UrlEncodedFormBody}, {@link MultipartBody}...
     */
    protected HttpBody createHttpBody() {return null;}
}
