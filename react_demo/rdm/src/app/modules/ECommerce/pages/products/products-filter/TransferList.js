/* eslint-disable no-restricted-imports */
import React from "react";
import {makeStyles} from "@material-ui/core/styles";
import {
  List,
  ListItem,
  Checkbox,
  Grid,
  Button,
  ListItemText,
  ListItemIcon,
  Card,
  CardHeader,
  Divider
} from "@material-ui/core";

const useStyles2 = makeStyles(theme => ({
  root: {
    margin: "auto"
  },
  cardHeader: {
    padding: theme.spacing(1, 2)
  },
  list: {
    width: 200,
    height: 230,
    backgroundColor: theme.palette.background.paper,
    overflow: "auto"
  },
  button: {
    margin: theme.spacing(0.5, 0)
  }
}));

function not2(a, b) {
  return a.filter(value => b.indexOf(value) === -1);
}

function intersection2(a, b) {
  return a.filter(value => b.indexOf(value) !== -1);
}

function union2(a, b) {
  return [...a, ...not2(b, a)];
}

export default function TransferListExamplesPage() {

  // Example 2
  const classes2 = useStyles2();
  const [checked2, setChecked2] = React.useState([]);
  const [left2, setLeft2] = React.useState([0, 1, 2, 3]);
  const [right2, setRight2] = React.useState([4, 5, 6, 7]);

  const leftChecked2 = intersection2(checked2, left2);
  const rightChecked2 = intersection2(checked2, right2);

  const handleToggle2 = value => () => {
    const currentIndex2 = checked2.indexOf(value);
    const newChecked2 = [...checked2];

    if (currentIndex2 === -1) {
      newChecked2.push(value);
    } else {
      newChecked2.splice(currentIndex2, 1);
    }

    setChecked2(newChecked2);
  };

  const numberOfChecked2 = items => intersection2(checked2, items).length;

  const handleToggleAll2 = items => () => {
    if (numberOfChecked2(items) === items.length) {
      setChecked2(not2(checked2, items));
    } else {
      setChecked2(union2(checked2, items));
    }
  };

  const handleCheckedRight2 = () => {
    setRight2(right2.concat(leftChecked2));
    setLeft2(not2(left2, leftChecked2));
    setChecked2(not2(checked2, leftChecked2));
  };

  const handleCheckedLeft2 = () => {
    setLeft2(left2.concat(rightChecked2));
    setRight2(not2(right2, rightChecked2));
    setChecked2(not2(checked2, rightChecked2));
  };

  const customList2 = (title, items) => (
    <Card>
      <CardHeader
        className={classes2.cardHeader}
        avatar={
          <Checkbox
            onClick={handleToggleAll2(items)}
            checked={
              numberOfChecked2(items) === items.length && items.length !== 0
            }
            indeterminate={
              numberOfChecked2(items) !== items.length &&
              numberOfChecked2(items) !== 0
            }
            disabled={items.length === 0}
            inputProps={{ "aria-label": "all items selected" }}
          />
        }
        title={title}
        subheader={`${numberOfChecked2(items)}/${items.length} selected`}
      />
      <Divider />
      <List className={classes2.list} dense component="div" role="list">
        {items.map(value => {
          const labelId = `transfer-list-all-item-${value}-label`;

          return (
            <ListItem
              key={value}
              role="listitem"
              button
              onClick={handleToggle2(value)}
            >
              <ListItemIcon>
                <Checkbox
                  checked={checked2.indexOf(value) !== -1}
                  tabIndex={-1}
                  disableRipple
                  inputProps={{ "aria-labelledby": labelId }}
                />
              </ListItemIcon>
              <ListItemText id={labelId} primary={`List item ${value + 1}`} />
            </ListItem>
          );
        })}
        <ListItem />
      </List>
    </Card>
  );

  return (
    <>

      <div className="row">
        <div className="col-md-12">
          <Grid
              container
              spacing={2}
              justify="center"
              alignItems="center"
              className={classes2.root}
          >
            <Grid item>{customList2("Choices", left2)}</Grid>
            <Grid item>
              <Grid container direction="column" alignItems="center">
                <Button
                    variant="outlined"
                    size="small"
                    className={classes2.button}
                    onClick={handleCheckedRight2}
                    disabled={leftChecked2.length === 0}
                    aria-label="move selected right"
                >
                  <i className="flaticon2-next" />
                </Button>
                <Button
                    variant="outlined"
                    size="small"
                    className={classes2.button}
                    onClick={handleCheckedLeft2}
                    disabled={rightChecked2.length === 0}
                    aria-label="move selected left"
                >
                  <i className="flaticon2-back" />
                </Button>
              </Grid>
            </Grid>
            <Grid item>{customList2("Chosen", right2)}</Grid>
          </Grid>
        </div>
      </div>
    </>
  );
}
