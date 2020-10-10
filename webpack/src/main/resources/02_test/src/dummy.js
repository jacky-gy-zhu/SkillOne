import data from './data/data.json';
import './style.css';
import './css.less';
import './font/iconfont.css';

function add(x, y) {
  return x + y;
}

const subtract = function subtract(x, y) {
  return x - y;
}; // eslint-disable-next-line

console.log(data); // eslint-disable-next-line

console.log(add(1, 2));
const promise = new Promise((resolve) => {
  setTimeout(() => {
    console.log('------------');
    resolve(124);
  }, 3000);
});
console.log('promise', promise);
promise.then((response) => {
  console.log('response', subtract(5, response * 1));
});
