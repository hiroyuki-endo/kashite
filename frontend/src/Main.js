import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Link, Switch } from 'react-router-dom';
import { useSelector } from "react-redux";
import './App.css';
import { Layout, Menu } from 'antd';
import 'antd/dist/antd.css';
import AAA from './AAA';
import BookSearch from './components/BookSearch';
import errorNotification from './components/ErrorNotification'
import {
  SearchOutlined,
  PieChartOutlined,
} from '@ant-design/icons';

const { Header, Sider, Content } = Layout;

function Main() {
  const [collapsed, setCollapsed] = useState(false);
  const errors = useSelector(state => state.errors);
  const error = errors.slice(-1)[0]
  errorNotification(error)

  return (
    <div className="Main">
      <Router>
        <Layout style={{ height: "100vh" }}>
          <Header></Header>
          <Layout>
            <Sider collapsible collapsed={collapsed} onCollapse={collapsed => setCollapsed(collapsed)}>
              <div className="logo" />
              <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline">
                <Menu.Item key="1" icon={<SearchOutlined />}>
                  <Link to="/kashite/main/search">本検索</Link>
                </Menu.Item>
                <Menu.Item key="2" icon={<PieChartOutlined />}>
                  <Link to="/kashite/main/rental">レンタル本</Link>
                </Menu.Item>
              </Menu>
            </Sider>
            <Content>
              <Switch>
                <Route path='/kashite/main/search' render={() => <BookSearch />} />
                <Route path='/kashite/main/rental' render={() => <BookSearch />} />
                <Route path='/AAA' render={() => <AAA />} />
              </Switch>
            </Content>
          </Layout>
        </Layout>
      </Router>
    </div >
  );
}

export default Main;
