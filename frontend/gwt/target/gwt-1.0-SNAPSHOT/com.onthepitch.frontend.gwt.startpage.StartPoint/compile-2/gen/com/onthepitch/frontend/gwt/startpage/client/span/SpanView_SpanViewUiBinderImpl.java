// .ui.xml template last modified: 1579978477186
package com.onthepitch.frontend.gwt.startpage.client.span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.uibinder.client.UiBinderUtil;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiBinderUtil;
import com.google.gwt.user.client.ui.Widget;

public class SpanView_SpanViewUiBinderImpl implements UiBinder<com.google.gwt.user.client.ui.Widget, com.onthepitch.frontend.gwt.startpage.client.span.SpanView>, com.onthepitch.frontend.gwt.startpage.client.span.SpanView.SpanViewUiBinder {

  interface Template extends SafeHtmlTemplates {
    @Template("<span> Span span span! </span> <span> More span span span! </span> <span> And more span span span! </span> <span> More more and more span span span! </span> <span> MOOOOORRRREEEE Span span span! </span> <span> Span span span! </span> <span> Enough </span>")
    SafeHtml html1();
     
  }

  Template template = GWT.create(Template.class);


  public com.google.gwt.user.client.ui.Widget createAndBindUi(final com.onthepitch.frontend.gwt.startpage.client.span.SpanView owner) {


    return new Widgets(owner).get_f_HTMLPanel1();
  }

  /**
   * Encapsulates the access to all inner widgets
   */
  class Widgets {
    private final com.onthepitch.frontend.gwt.startpage.client.span.SpanView owner;


    public Widgets(final com.onthepitch.frontend.gwt.startpage.client.span.SpanView owner) {
      this.owner = owner;
    }

    SafeHtml template_html1() {
      return template.html1();
    }

    /**
     * Getter for clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay called 0 times. Type: GENERATED_BUNDLE. Build precedence: 1.
     */
    private com.onthepitch.frontend.gwt.startpage.client.span.SpanView_SpanViewUiBinderImpl_GenBundle get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      return build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay();
    }
    private com.onthepitch.frontend.gwt.startpage.client.span.SpanView_SpanViewUiBinderImpl_GenBundle build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      // Creation section.
      final com.onthepitch.frontend.gwt.startpage.client.span.SpanView_SpanViewUiBinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = (com.onthepitch.frontend.gwt.startpage.client.span.SpanView_SpanViewUiBinderImpl_GenBundle) GWT.create(com.onthepitch.frontend.gwt.startpage.client.span.SpanView_SpanViewUiBinderImpl_GenBundle.class);
      // Setup section.

      return clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay;
    }

    /**
     * Getter for f_HTMLPanel1 called 1 times. Type: DEFAULT. Build precedence: 1.
     */
    private com.google.gwt.user.client.ui.HTMLPanel get_f_HTMLPanel1() {
      return build_f_HTMLPanel1();
    }
    private com.google.gwt.user.client.ui.HTMLPanel build_f_HTMLPanel1() {
      // Creation section.
      final com.google.gwt.user.client.ui.HTMLPanel f_HTMLPanel1 = new com.google.gwt.user.client.ui.HTMLPanel(template_html1().asString());
      // Setup section.

      return f_HTMLPanel1;
    }
  }
}
