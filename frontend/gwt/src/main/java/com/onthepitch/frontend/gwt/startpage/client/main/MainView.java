package com.onthepitch.frontend.gwt.startpage.client.main;

import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.onthepitch.frontend.gwt.startpage.client.matches.MatchesView;
import com.onthepitch.frontend.gwt.startpage.client.post.PostView;

public class MainView extends Composite {

    /**
     * Title of this page.
     */
    @UiField
    //@checkstyle VisibilityModifierCheck (2 lines)
    protected SpanElement title;

    /**
     * Workspace.
     */
    @UiField
    //@checkstyle VisibilityModifierCheck (2 lines)
    protected HTMLPanel workspace;

    /**
     * UI binder.
     */
    private final MainViewUiBinder binder;

    /**
     * Table view.
     */

    /**
     * Beutifull span.
     */

    //   private final LoginView loginView;

    private final PostView postView;
    private final MatchesView matchesView;

    /**
     * Constructor which create a view according to markup from MainView.ui.xml.
     *
     * @param binder Ui binder for this view.
     */
    @Inject
    public MainView(
            final MainViewUiBinder binder,
            //  final LoginView loginView
            final PostView postView,
            final MatchesView matchesView
    ) {
        this.binder = binder;
        //  this.loginView = loginView;
        this.postView = postView;
        this.matchesView = matchesView;
    }

    /**
     * Init our view.
     *
     * @return This object.
     */
    public final MainView init() {
        super.initWidget(this.binder.createAndBindUi(this));
        //  this.loginView.init();
        this.matchesView.init();
        this.postView.init();

        return this;
    }

    /**
     * Display table.
     *
     * @param event Event.
     */

//    @UiHandler("buttonLogin")
//    public void onLoginButton(final ClickEvent event) {
//        this.workspace.clear();
//        this.workspace.add(this.loginView);
//    }

    /**
     * Display span span span.
     *
     * @param event Event
     */

//    @UiHandler("buttonPost")
//    public void onPostButton(final ClickEvent event) {
//        this.workspace.clear();
//        this.workspace.add(this.postView);
//        this.title.setInnerText(this.postView.getName());
//    }

    /**
     * Interface for autoimplementation of our view.
     *
     * @since 0.0.1
     */
    interface MainViewUiBinder extends UiBinder<Widget, MainView> {
    }
}