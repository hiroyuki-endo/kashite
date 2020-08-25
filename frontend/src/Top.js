import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { withRouter } from 'react-router-dom';
import useReactRouter from 'use-react-router';
import { Layout, Button } from 'antd';
import 'antd/dist/antd.css';
import SignIn from './components/SignIn';
import SignUp from './components/SignUp';
import './Top.css';

const { Header, Content } = Layout;

function Top() {

  const { history } = useReactRouter();

  return (
    <div className="Front">
      <Layout style={{ height: "100vh" }}>
        <Header className="top-header">
          <Button className="header-signin" type="link" onClick={v => history.push('/kashite/top/signin')} >sign in</Button>
          <Button className="header-signup" onClick={v => history.push('/kashite/top/signup')} >Sing up</Button>
        </Header>
        <Layout>
          <Content>
            <Switch>
              {/* <Route path='/kashite/top/' render={() => <SignIn />} /> */}
              <Route path='/kashite/top/signin' render={() => <SignIn />} />
              <Route path='/kashite/top/signup' render={() => <SignUp />} />
            </Switch>
          </Content>
        </Layout>
      </Layout>
    </div >
  );
}

export default withRouter(Top);
