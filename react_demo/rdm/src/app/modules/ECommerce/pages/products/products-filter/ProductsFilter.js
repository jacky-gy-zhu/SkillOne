import React, { useMemo } from "react";
import { Formik } from "formik";
import { isEqual } from "lodash";
import { useProductsUIContext } from "../ProductsUIContext";
import {Button, ButtonToolbar, Col, Form, Modal, Row, Tab, Tabs} from "react-bootstrap";
import {DatePickerField, KTCodeExample} from "../../../../../../_metronic/_partials/controls";

const prepareFilter = (queryParams, values) => {
  const { status, condition, searchText } = values;
  const newQueryParams = { ...queryParams };
  const filter = {};
  // Filter by status
  filter.status = status !== "" ? +status : undefined;
  // Filter by condition
  filter.condition = condition !== "" ? +condition : undefined;
  // Filter by all fields
  filter.model = searchText;
  if (searchText) {
    filter.manufacture = searchText;
    filter.VINCode = searchText;
  }
  newQueryParams.filter = filter;
  return newQueryParams;
};

export function ProductsFilter({ listLoading }) {
  // Products UI Context
  const productsUIContext = useProductsUIContext();
  const productsUIProps = useMemo(() => {
    return {
      setQueryParams: productsUIContext.setQueryParams,
      queryParams: productsUIContext.queryParams,
    };
  }, [productsUIContext]);

  const applyFilter = (values) => {
    const newQueryParams = prepareFilter(productsUIProps.queryParams, values);
    if (!isEqual(newQueryParams, productsUIProps.queryParams)) {
      newQueryParams.pageNumber = 1;
      productsUIProps.setQueryParams(newQueryParams);
    }
  };

  return (
    <>
      <Formik
        initialValues={{
          status: "", // values => All=""/Selling=0/Sold=1
          condition: "", // values => All=""/New=0/Used=1
          searchText: "",
        }}
        onSubmit={(values) => {
          applyFilter(values);
        }}
      >
        {({
          values,
          handleSubmit,
          handleBlur,
          handleChange,
          setFieldValue,
        }) => (
          <form onSubmit={handleSubmit} className="form form-label-right">
            <div className="form-group row">
              <div className="col-lg-2">
                <select
                  className="form-control"
                  name="status"
                  placeholder="Filter by Status"
                  onChange={(e) => {
                    setFieldValue("status", e.target.value);
                    handleSubmit();
                  }}
                  onBlur={handleBlur}
                  value={values.status}
                >
                  <option value="">All</option>
                  <option value="0">Pending</option>
                  <option value="1">Approved</option>
                </select>
                <small className="form-text text-muted">
                  <b>Filter</b> by Status
                </small>
              </div>

              <div className="col-lg-2">
                <input
                  type="text"
                  className="form-control"
                  name="searchText"
                  placeholder="Search"
                  onBlur={handleBlur}
                  value={values.searchText}
                  onChange={(e) => {
                    setFieldValue("searchText", e.target.value);
                    handleSubmit();
                  }}
                />
                <small className="form-text text-muted">
                  <b>Search</b> in all fields
                </small>
              </div>

              <div className="col-lg-2">
                <AdvanceSearchApp />
              </div>
            </div>
          </form>
        )}
      </Formik>
    </>
  );
}

class MyVerticallyCenteredModal extends React.Component {

  render() {
    return (
        <Modal
            {...this.props}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
          <Modal.Header closeButton>
            <Modal.Title id="contained-modal-title-vcenter">
              Agreement Search
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form>
              <Tabs defaultActiveKey="general" id="uncontrolled-tab-example">
                <Tab eventKey="general" title="General">
                  <div className="my-7"></div>
                  <span>Agreement</span>
                  <div className="separator separator-dashed my-7"></div>
                  <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                      Agreement ID:
                    </Form.Label>
                    <Col sm="8">
                      <Form.Control type="text" placeholder="" />
                    </Col>
                  </Form.Group>
                  <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                      Agreement Desc:
                    </Form.Label>
                    <Col sm="8">
                      <Form.Control type="text" placeholder="" />
                    </Col>
                  </Form.Group>
                  <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                      Vendor:
                    </Form.Label>
                    <Col sm="8">
                      <Form.Control as="select">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                      </Form.Control>
                    </Col>
                  </Form.Group>
                  <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                      Start Date:
                    </Form.Label>
                    <Col sm="8">
                      <DatePickerField
                          name="dateOfBbirth"
                          label=""
                      />
                    </Col>
                  </Form.Group>
                  <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                      End Date:
                    </Form.Label>
                    <Col sm="8">
                      <DatePickerField
                          name="dateOfBbirth2"
                          label=""
                      />
                    </Col>
                  </Form.Group>
                </Tab>
                <Tab eventKey="advanced" title="Advanced">
                  <div className="my-7"></div>
                  <span>Rule</span>
                  <div className="separator separator-dashed my-7"></div>
                  <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                      Rule ID:
                    </Form.Label>
                    <Col sm="8">
                      <Form.Control type="text" placeholder="" />
                    </Col>
                  </Form.Group>
                  <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                      Rule Description:
                    </Form.Label>
                    <Col sm="8">
                      <Form.Control type="text" placeholder="" />
                    </Col>
                  </Form.Group>
                  <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                      Buying Group:
                    </Form.Label>
                    <Col sm="8">
                      <Form.Control as="select">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                      </Form.Control>
                    </Col>
                  </Form.Group>
                </Tab>
                <Tab eventKey="contributes" title="Contributes">
                  <div className="my-7"></div>
                  <span>Contributes</span>
                  <div className="separator separator-dashed my-7"></div>
                  <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                      Creator:
                    </Form.Label>
                    <Col sm="8">
                      <Form.Control as="select">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                      </Form.Control>
                    </Col>
                  </Form.Group>
                  <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                      Category Manager:
                    </Form.Label>
                    <Col sm="8">
                      <Form.Control as="select">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                      </Form.Control>
                    </Col>
                  </Form.Group>
                  <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                      Buyer:
                    </Form.Label>
                    <Col sm="8">
                      <Form.Control as="select">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                      </Form.Control>
                    </Col>
                  </Form.Group>
                  <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                      Approvers:
                    </Form.Label>
                    <Col sm="8">
                      <Form.Control as="select">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                      </Form.Control>
                    </Col>
                  </Form.Group>
                </Tab>
              </Tabs>
            </Form>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="primary" onClick={this.props.onHide}>Search</Button>
            <Button variant="secondary" onClick={this.props.onHide}>Close</Button>
          </Modal.Footer>
        </Modal>
    );
  }
}

class AdvanceSearchApp extends React.Component {
  constructor(...args) {
    super(...args);

    this.state = { modalShow: false };
  }

  render() {
    let modalClose = () => this.setState({ modalShow: false });

    return (
        <ButtonToolbar>
          <Button
              variant="primary"
              onClick={() => this.setState({ modalShow: true })}
          >
            Advance Search
          </Button>

          <MyVerticallyCenteredModal
              show={this.state.modalShow}
              onHide={modalClose}
          />
        </ButtonToolbar>
    );
  }
}