package com.onthepitch.frontend.gwt.startpage.client.post;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.onthepitch.shared.model.PostResult;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostView extends Composite {
    //    @UiField
//    //@checkstyle VisibilityModifierCheck (2 lines)
//    protected Label username;
//    @UiField
//    protected Label header;
//    @UiField
//    protected Label text;
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
        CellTable<PostResult> table = new CellTable<PostResult>();
        createTable(table);
        VerticalPanel panel = new VerticalPanel();
        panel.add(table);
        RootPanel.get().add(panel);
        return this;
    }

    private ListDataProvider<PostResult> createTable(CellTable<PostResult> table) {
        TextColumn<PostResult> headerColumn = new TextColumn<PostResult>() {
            @Override
            public String getValue(PostResult postResult) {
                return postResult.getHeader();
            }
        };
        TextColumn<PostResult> textColumn = new TextColumn<PostResult>() {
            @Override
            public String getValue(PostResult postResult) {
                return postResult.getText();
            }
        };
        table.addColumn(headerColumn, "Header");
        table.addColumn(textColumn, "Text");
        ListDataProvider<PostResult> dataProvider = new ListDataProvider<PostResult>();
        dataProvider.addDataDisplay(table);
        this.rest.showPosts(new MethodCallback<List<PostResult>>() {

            @Override
            public void onFailure(final Method method, final Throwable throwable) {
                GWT.log("Error", throwable);
            }

            @Override
            public void onSuccess(final Method method, final List<PostResult> results) {
                dataProvider.getList().addAll(results);
            }
        });
        return dataProvider;
    }
//    @UiHandler("show")
//    public void showClick(final ClickEvent event) {
//        CellTable<PostResult> table = new CellTable<PostResult>();
//        // Create name column.
//        TextColumn<PostResult> headerColumn = new TextColumn<PostResult>() {
//            @Override
//            public String getValue(PostResult postResult) {
//                return postResult.getHeader();
//            }
//        };
//
//        // Create address column.
//        TextColumn<PostResult> textColumn = new TextColumn<PostResult>() {
//            @Override
//            public String getValue(PostResult postResult) {
//                return postResult.getText();
//            }
//        };
//        table.addColumn(headerColumn,"Header");
//        table.addColumn(textColumn,"Text");
//
//        this.rest.showPosts(
//                new MethodCallback<List<PostResult>>() {
//
//                    @Override
//                    public void onFailure(final Method method, final Throwable throwable) {
//                        Window.alert("Error: " + throwable.getMessage());
//                    }
//
//                    @Override
//                    public void onSuccess(final Method method, final List<PostResult> results) {
//                        table.setRowCount(results.size(),true);
//                        table.setRowData(results);
//
////                        PostView.this.username.setText(results.getUsername());
////                        PostView.this.header.setText(results.getHeader());
////                        PostView.this.text.setText(results.getText());
//                    }
//                });
//        RootPanel.get().add(table);
//    }

    interface PostViewUiBinder extends UiBinder<Widget, PostView> {
    }
}
