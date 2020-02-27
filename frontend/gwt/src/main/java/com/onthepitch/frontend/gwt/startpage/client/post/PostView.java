package com.onthepitch.frontend.gwt.startpage.client.post;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.onthepitch.shared.model.PostResult;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.springframework.beans.factory.annotation.Autowired;

public class PostView extends Composite {
    @UiField
    //@checkstyle VisibilityModifierCheck (2 lines)
    protected Label username;
    @UiField
    protected Label header;
    @UiField
    protected Label text;
    private PostViewUiBinder binder;
    private PostRest rest;

    @Inject
    @Autowired
    public PostView(PostViewUiBinder binder, PostRest rest) {
        this.binder = binder;
        this.rest = rest;
    }

    public final PostView init() {
        super.initWidget(this.binder.createAndBindUi(this));
        return this;
    }

    @UiHandler("show")
    public void showClick(final ClickEvent event) {
        ListDataProvider<PostResult> listDataProvider = new ListDataProvider<>();
        this.rest.showPosts(
                new MethodCallback<PostResult>() {

                    @Override
                    public void onFailure(final Method method, final Throwable throwable) {
                        Window.alert("Error: ");
                    }

                    @Override
                    public void onSuccess(final Method method, final PostResult results) {
                        PostView.this.username.setText(results.getUsername());
                        PostView.this.header.setText(results.getHeader());
                        PostView.this.text.setText(results.getText());
                    }
                });
    }

    interface PostViewUiBinder extends UiBinder<Widget, PostView> {
    }
}
