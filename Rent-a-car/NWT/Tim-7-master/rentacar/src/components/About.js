import React, {Component} from 'react';
import { Table, Collapse, Divider } from 'antd';
import 'antd/dist/antd.css';

const Panel = Collapse.Panel;

const text1 = `
We are company that offers you great cars with minimum costs. All cars we rent are in presigious condition, bought new, and serviced in 
authorized services.
`;
const text2 = `
We have a wide range of cars to offer, starting from VolksWagen, Opel, Renault, Peugeot, Toyota, Volvo and so on. Renting car is very simple, you come to our saloon, pick the 
right car for you and hit the road. Simple as that!
`;
const text3 = `
When you pick the right car for you, we take your credientals, fill the form and you sign there.
`;
const text4 = `
We are currently located in Kampus-University of Sarajevo, near Faculty of electrical engineering.
`;

function callback(key) {
    console.log(key);
  }

export default class About extends Component{

    render(){
        return (
            <Collapse defaultActiveKey={['1']} onChange={callback}>
    <Panel header="Who are we?" key="1">
      <p>{text1}</p>
    </Panel>
    <Panel header="What we can offer to you?" key="2">
      <p>{text2}</p>
    </Panel>
    <Panel header="How to buy?" key="3">
      <p>{text3}</p>
    </Panel>
    <Panel header="Where are we?" key="4">
      <p>{text4}</p>
    </Panel>
    <Divider type="horizontal" />
    <a href="/contact"> Have another questions? Contact Us</a>
  </Collapse>
        )
    }
}