package com.onthepitch.frontend.gwt.startpage.client.matches;

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
import com.onthepitch.frontend.gwt.startpage.client.post.PostView;
import com.onthepitch.shared.model.MatchesResult;
import com.onthepitch.shared.model.PostResult;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MatchesView extends Composite {
    private MatchesViewUiBinder binder;
    private MatchesRest rest;
    
    @Inject
    @Autowired
    MatchesView(MatchesViewUiBinder binder,MatchesRest rest){
        this.binder = binder;
        this.rest = rest;
    }
    
    public final MatchesView init() {
        super.initWidget(this.binder.createAndBindUi(this));
        CellTable<MatchesResult> table = new CellTable<MatchesResult>();
        createTable(table);
        VerticalPanel panel = new VerticalPanel();
        panel.add(table);
        RootPanel.get().add(panel);
        return this;
    }

    private ListDataProvider<MatchesResult> createTable(CellTable<MatchesResult> table) {
        TextColumn<MatchesResult> homeTeam = new TextColumn<MatchesResult>() {
            @Override
            public String getValue(MatchesResult matchesResult) {
                return matchesResult.getHomeTeam_name();
            }
        };
        TextColumn<MatchesResult> homeTeamScore = new TextColumn<MatchesResult>() {
            @Override
            public String getValue(MatchesResult matchesResult) {
                return matchesResult.getHomeTeamScored().toString();
            }
        };
        TextColumn<MatchesResult> awayTeam = new TextColumn<MatchesResult>() {
            @Override
            public String getValue(MatchesResult matchesResult) {
                return matchesResult.getAwayTeam_name();
            }
        };
        TextColumn<MatchesResult> awayTeamScored = new TextColumn<MatchesResult>() {
            @Override
            public String getValue(MatchesResult matchesResult) {
                return matchesResult.getAwayTeamScored().toString();
            }
        };
        TextColumn<MatchesResult> leagueName = new TextColumn<MatchesResult>() {
            @Override
            public String getValue(MatchesResult matchesResult) {
                return matchesResult.getLeague_name();
            }
        };
        table.addColumn(homeTeam, "Home team");
        table.addColumn(homeTeamScore, "Home team scored");
        table.addColumn(awayTeam, "Away team");
        table.addColumn(awayTeamScored, "Away team scored");
        table.addColumn(leagueName, "League");
        ListDataProvider<MatchesResult> dataProvider = new ListDataProvider<MatchesResult>();
        dataProvider.addDataDisplay(table);
        this.rest.showPosts(new MethodCallback<List<MatchesResult>>() {

            @Override
            public void onFailure(final Method method, final Throwable throwable) {
                GWT.log("Error", throwable);
            }

            @Override
            public void onSuccess(final Method method, final List<MatchesResult> results) {
                dataProvider.getList().addAll(results);
            }
        });
        return dataProvider;
    }

    interface MatchesViewUiBinder extends UiBinder<Widget, MatchesView> {
    }
}
