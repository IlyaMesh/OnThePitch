package com.onthepitch.frontend.gwt.startpage.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.user.client.ui.RootPanel;
import com.onthepitch.frontend.gwt.startpage.client.main.MainView;
import com.onthepitch.frontend.gwt.startpage.client.post.PostView;
import org.fusesource.restygwt.client.Defaults;

public class StartPoint implements EntryPoint {

    /**
     * Injector field.
     */
    private final Injection injection;

    /**
     * Constructor. Create an injector.
     */
    public StartPoint() {
        this.injection = GWT.create(Injection.class);
    }

    @Override
    public void onModuleLoad()
    {
        Defaults.setServiceRoot("http://127.0.0.1:8080");
        RootPanel.get().add(this.injection.getMainView().init());
        RootPanel.get().add(this.injection.getPostView().init());
    }

    /**
     * Injector interface.
     *
     * @since 0.0.1
     */
    public interface Injection extends Ginjector {
        /**
         * Create a main view.
         * @return Main view.
         */
        MainView getMainView();

        PostView getPostView();

    }

}