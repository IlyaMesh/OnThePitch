// .ui.xml template last modified: 1579978691627
package com.onthepitch.frontend.gwt.startpage.client.table;

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

public class TableView_TableViewUiBinderImpl implements UiBinder<com.google.gwt.user.client.ui.Widget, com.onthepitch.frontend.gwt.startpage.client.table.TableView>, com.onthepitch.frontend.gwt.startpage.client.table.TableView.TableViewUiBinder {

  interface Template extends SafeHtmlTemplates {
    @Template("<table> <tr> <td>First row:</td> <td>Second row:</td> </tr> <tr> <td>Something one</td> <td>Something two</td> </tr> <tr> <td>One more one</td> <td>One more two</td> </tr> </table>")
    SafeHtml html1();
     
  }

  Template template = GWT.create(Template.class);


  public com.google.gwt.user.client.ui.Widget createAndBindUi(final com.onthepitch.frontend.gwt.startpage.client.table.TableView owner) {


    return new Widgets(owner).get_f_HTMLPanel1();
  }

  /**
   * Encapsulates the access to all inner widgets
   */
  class Widgets {
    private final com.onthepitch.frontend.gwt.startpage.client.table.TableView owner;


    public Widgets(final com.onthepitch.frontend.gwt.startpage.client.table.TableView owner) {
      this.owner = owner;
    }

    SafeHtml template_html1() {
      return template.html1();
    }

    /**
     * Getter for clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay called 0 times. Type: GENERATED_BUNDLE. Build precedence: 1.
     */
    private com.onthepitch.frontend.gwt.startpage.client.table.TableView_TableViewUiBinderImpl_GenBundle get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      return build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay();
    }
    private com.onthepitch.frontend.gwt.startpage.client.table.TableView_TableViewUiBinderImpl_GenBundle build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      // Creation section.
      final com.onthepitch.frontend.gwt.startpage.client.table.TableView_TableViewUiBinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = (com.onthepitch.frontend.gwt.startpage.client.table.TableView_TableViewUiBinderImpl_GenBundle) GWT.create(com.onthepitch.frontend.gwt.startpage.client.table.TableView_TableViewUiBinderImpl_GenBundle.class);
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
