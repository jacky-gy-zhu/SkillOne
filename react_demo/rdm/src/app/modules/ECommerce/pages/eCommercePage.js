import React, { Suspense } from "react";
import { Redirect, Switch } from "react-router-dom";
import { ListPage } from "./products/ProductsPage";
import { ProductEdit } from "./products/product-edit/ProductEdit";
import { LayoutSplashScreen, ContentRoute } from "../../../../_metronic/layout";

export default function eCommercePage() {
  return (
    <Suspense fallback={<LayoutSplashScreen />}>
      <Switch>
        {
          /* Redirect from eCommerce root URL to /customers */
          <Redirect
            exact={true}
            from="/rdm"
            to="/rdm/agreements"
          />
        }
        <ContentRoute path="/rdm/agreements/new" component={ProductEdit} />
        <ContentRoute
          path="/rdm/agreements/:id/edit"
          component={ProductEdit}
        />

        <ContentRoute path="/rdm/agreements" component={ListPage} />
      </Switch>
    </Suspense>
  );
}
