import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { useSelector } from "react-redux";
import { Layout, Button } from 'antd';
import 'antd/dist/antd.css';
import Login from './components/Login';
import errorNotification from './components/ErrorNotification'
import './Front.css';

const { Header, Content } = Layout;

function Front() {

  const errors = useSelector(state => state.errors);
  const error = errors.slice(-1)[0]
  errorNotification(error)

  return (
    <div className="Front">
      <Layout style={{ height: "100vh" }}>
        <Header className="top-header">
          <Button className="header-signin" type="link">Sing in</Button>
          <Button className="header-signup">Sing up</Button>
        </Header>
        <Layout>
          <Content>
            <Switch>
              <Route path='/' render={() => <Login />} />
            </Switch>
          </Content>
        </Layout>
      </Layout>
    </div >
  );
}

export default Front;
