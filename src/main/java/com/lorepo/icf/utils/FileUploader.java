package com.lorepo.icf.utils;

import java.util.List;
import java.util.ArrayList;

import com.google.gwt.dom.client.Element;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Widget;

import com.lorepo.icf.utils.ExtendedRequestBuilder;
import com.lorepo.icf.utils.JavaScriptUtils;
import com.lorepo.icf.utils.StringUtils;


public class FileUploader {
    
    public static class UploadFormData extends JavaScriptObject {
        
        protected UploadFormData() {};

        public static final native UploadFormData parse(String json) /*-{
            return eval('(' + json + ')');
        }-*/;

        public final native int getId() /*-{
            return this.id;
        }-*/;

        public final native String getUrl() /*-{
            return this.url;
        }-*/;

        public final native String getSuccessActionRedirect() /*-{
            return this.success_action_redirect;
        }-*/;

        public final native String getField(String fieldKey) /*-{
            return this.fields[fieldKey];
        }-*/;

        public final List<String> getFieldsKeys() {
            return JavaScriptUtils.convertJsArrayToArrayList(this.getKeys());
        };

        private final native JsArrayString getKeys() /*-{
            return Object.keys(this.fields);
        }-*/;
    }

    public static FormPanel createUploadForm() {
        FormPanel panel = new FormPanel();
        panel.setEncoding(FormPanel.ENCODING_MULTIPART);
        panel.setMethod(FormPanel.METHOD_POST);
        return panel;
    }

    public static FileUpload createFileUpload() {
        FileUpload fileUpload = new FileUpload();
        fileUpload.setName("file");
        return fileUpload;
    }

    public static void setPanel(FormPanel uploadForm, FileUpload fileUpload) {
        AbsolutePanel formPanel = new AbsolutePanel();
        uploadForm.setWidget(formPanel);
        formPanel.add(fileUpload);
    }

    public static void prepareFormPanelToSubmit(FormPanel uploadForm, UploadFormData uploadFormData) {
        // Note: Order of fields in this form is important
        // Data from upload file as inputs
        // ...
        // file input
        // success_action_redirect input
        AbsolutePanel formPanel = (AbsolutePanel) uploadForm.getWidget();

        uploadForm.setAction(uploadFormData.getUrl());

        preventUnwantedInputsDataToBeSentBySubmit(formPanel.getElement());

        String elementName;
        String elementValue;
        Hidden hiddenWidget;

        List<String> fieldsKeys = uploadFormData.getFieldsKeys();
        for (int i = 0; i < fieldsKeys.size(); i++) {
            elementName = fieldsKeys.get(i);
            elementValue = uploadFormData.getField(elementName);
            hiddenWidget = createHiddenWidget(elementName, elementValue);
            formPanel.insert(hiddenWidget, i);
        }

        elementName = "success_action_redirect";
        elementValue = uploadFormData.getSuccessActionRedirect();
        hiddenWidget = createHiddenWidget(elementName, elementValue);
        formPanel.add(hiddenWidget);
    }

    private native static void preventUnwantedInputsDataToBeSentBySubmit(Element formPanel) /*-{
        var inputs = formPanel.getElementsByTagName("input");
        for (var i = 0; i < inputs.length; i++) {
            if (inputs[i].getAttribute("name") !== "file") {
                inputs[i].setAttribute("disabled", '');
            }
        }
    }-*/;

    private static Hidden createHiddenWidget(String name, String value) {
        Hidden hiddenWidget = new Hidden();

        hiddenWidget.setName(name);
        hiddenWidget.setValue(value);

        return hiddenWidget;
    }

    public native static String createUploadedEndpoint(String successActionRedirect, String filename, String contentType) /*-{
        var encodeUnicodeStringToBase64 = $entry(function(unicodeString) {
            return @com.lorepo.icf.utils.StringUtils::encodeUnicodeStringToBase64(Ljava/lang/String;)(unicodeString);
        });

        var metadata = {
            filename: filename,
            content_type: contentType
        };

        var metadataStr = encodeUnicodeStringToBase64(JSON.stringify(metadata));
        return successActionRedirect + "&metadata=" + metadataStr;
    }-*/;
    
    public static native String getFileIdFromResponse(String responseText) /*-{
        var obj = JSON.parse(responseText);
        return obj.uploaded_file_id;
    }-*/;
    
    public static native String getSelectedFileContentType(Element fileUploadElement) /*-{
        var file = fileUploadElement.files[0];
        return file.type;
    }-*/;
    
    public static native String getSelectedFileName(Element fileUploadElement) /*-{
        var file = fileUploadElement.files[0];
        return file.name;
    }-*/;
    
    public static void sendRequest(String url, RequestCallback callback) throws RequestException {
        ExtendedRequestBuilder builder = new ExtendedRequestBuilder(ExtendedRequestBuilder.GET, URL.encode(url));
        builder.sendRequest("",  callback);
    }
}
