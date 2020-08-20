import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { useSelector } from "react-redux";
import './App.css';
import { Layout, Menu } from 'antd';
import 'antd/dist/antd.css';
import Auth from './Auth'
import BookSearch from './components/BookSearch';
import Login from './components/Login';
import Main from './Main'
import errorNotification from './components/ErrorNotification'
import {
  SearchOutlined,
  PieChartOutlined,
} from '@ant-design/icons';

const { Header, Sider, Content } = Layout;

function App() {
  const [collapsed, setCollapsed] = useState(false);
  const errors = useSelector(state => state.errors);
  const error = errors.slice(-1)[0]
  errorNotification(error)

  return (
    <div className="App">
      <Router>
        <Switch>
          <Route path='/login' render={() => <Login />} />
          <Route path='/' render={() => <Auth children={Main} />} />
        </Switch>
      </Router>
    </div >
  );
}

export default App;
