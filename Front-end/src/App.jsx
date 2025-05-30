import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Home from './Pages/Home/Home';
import Menu from './Pages/Home/MenuBar';
import Login from './Pages/Forms/Login';
import 'bootstrap-icons/font/bootstrap-icons.css';
import { Route, Routes } from 'react-router-dom';
import ProjectDetails from './Pages/ProjectDetails/ProjectDetails';
import Subscription from './Pages/Forms/Subscription';
function App() {
  return (
    <>
      <Menu />
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/projectDetails/:id" element={<ProjectDetails />} />
        <Route path="/subscription" element={<Subscription />} />
        <Route path="/projects" element={<Home />} />
      </Routes>
    </>
  );
}

export default App;
