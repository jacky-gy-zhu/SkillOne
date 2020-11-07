import React from "react";
import { Route } from "react-router-dom";
import { ProductsLoadingDialog } from "./products-loading-dialog/ProductsLoadingDialog";
import { ProductDeleteDialog } from "./product-delete-dialog/ProductDeleteDialog";
import { ProductsDeleteDialog } from "./products-delete-dialog/ProductsDeleteDialog";
import { ProductsFetchDialog } from "./products-fetch-dialog/ProductsFetchDialog";
import { ProductsUpdateStatusDialog } from "./products-update-status-dialog/ProductsUpdateStatusDialog";
import { ProductsCard } from "./ProductsCard";
import { ProductsUIProvider } from "./ProductsUIContext";

export function ListPage({ history }) {
  const productsUIEvents = {
    newProductButtonClick: () => {
      history.push("/rdm/agreements/new");
    },
    openEditProductPage: (id) => {
      history.push(`/rdm/agreements/${id}/edit`);
    },
    openDeleteProductDialog: (id) => {
      history.push(`/rdm/agreements/${id}/delete`);
    },
    openDeleteProductsDialog: () => {
      history.push(`/rdm/agreements/deleteProducts`);
    },
    openFetchProductsDialog: () => {
      history.push(`/rdm/agreements/fetch`);
    },
    openUpdateProductsStatusDialog: () => {
      history.push("/rdm/agreements/updateStatus");
    },
  };

  return (
    <ProductsUIProvider productsUIEvents={productsUIEvents}>
      <ProductsLoadingDialog />
      <Route path="/rdm/agreements/deleteProducts">
        {({ history, match }) => (
          <ProductsDeleteDialog
            show={match != null}
            onHide={() => {
              history.push("/rdm/agreements");
            }}
          />
        )}
      </Route>
      <Route path="/rdm/agreements/:id/delete">
        {({ history, match }) => (
          <ProductDeleteDialog
            show={match != null}
            id={match && match.params.id}
            onHide={() => {
              history.push("/rdm/agreements");
            }}
          />
        )}
      </Route>
      <Route path="/rdm/agreements/fetch">
        {({ history, match }) => (
          <ProductsFetchDialog
            show={match != null}
            onHide={() => {
              history.push("/rdm/agreements");
            }}
          />
        )}
      </Route>
      <Route path="/rdm/agreements/updateStatus">
        {({ history, match }) => (
          <ProductsUpdateStatusDialog
            show={match != null}
            onHide={() => {
              history.push("/rdm/agreements");
            }}
          />
        )}
      </Route>
      <ProductsCard />
    </ProductsUIProvider>
  );
}
