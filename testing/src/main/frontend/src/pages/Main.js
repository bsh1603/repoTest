import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useRecoilState } from "recoil";
import NavBar from "../components/NavBar";
import { userState } from "../recoil/atom";

const Main = () => {
  const [user, setUser] = useRecoilState(userState);
  const navigate = useNavigate();

  useEffect(() => {
    if (!user.id) {
      const cache = JSON.parse(localStorage.getItem("user"));
      if (!cache) navigate("/login");
      setUser(cache);
    }
  }, []);
  return (
    <>
      <NavBar />
      <div>메인 페이지</div>
      <h1>{user.name}</h1>
      <h1>{user.admin}</h1>
    </>
  );
};

export default Main;