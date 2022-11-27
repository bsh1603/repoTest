import { Route, Routes } from "react-router-dom";

import ChatSetting from "./pages/ChatSetting";
import Login from "./pages/Login";
import Main from "./pages/Main";
import Member from "./pages/Member";
import Profile from "./pages/Profile";
import RegisterManager from "./pages/RegisterManager";
import RegisterWorker from "./pages/RegisterWorker";
import Stock from "./pages/Stock";
import Work from "./pages/Work";
import Graph from "./pages/Graph";
import {atom, useAtom} from "jotai";
import {useEffect} from "react";

export const loginInfo = atom(null)

function App() {
  const [user,setUser ] = useAtom(loginInfo);
  useEffect(() => {
    const getSS = JSON.parse(sessionStorage.getItem("user"))
    console.log(getSS);
    if(getSS){
        setUser(getSS);
        console.log("통과되나요");
    }
  }, []);
  return (
    <>
      <Routes>
        <Route path="/" element={<div>루트페이지</div>} />
            <Route path="/main" element={<Main />} />
            <Route path="/registermanager" element={<RegisterManager />} />
            <Route path="/registerworker" element={<RegisterWorker />} />
            <Route path="/login" element={<Login />} />
            <Route path="/work" element={<Work />} />
            <Route path="member" element={<Member />} />
            <Route path="/stock" element={<Stock />} />
            <Route path="/chatsetting" element={<ChatSetting />} />
            <Route path="/profile" element={<Profile />} />
            <Route path="/graph" element={<Graph />} />
      </Routes>
    </>
  );
}

export default App;
