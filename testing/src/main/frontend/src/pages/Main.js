import axios from "axios";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useRecoilState } from "recoil";
import NavBar from "../components/NavBar";
import { isWorkingState, userState } from "../recoil/atom";
import WorkChart from "../components/WorkChart"

const Main = () => {
  const [user, setUser] = useRecoilState(userState);
  const [isWorking, setIsWorking] = useRecoilState(isWorkingState);
  const navigate = useNavigate();

  const validateLocation = (myLat, myLong, latitude, longitude) => {
    const diffLat = Math.abs(myLat - latitude);
    const diffLong = Math.abs(myLong - longitude);
    if (diffLat < 0.0006 && diffLong < 0.0006) return true;
    return false;
  };

  const getCurrentTime = () => {
    const date = new Date();
    const [hour, minute] = date.toTimeString().split(" ")[0].split(":");
    const [year, month, day] = date.toLocaleString().split(". ");
    return `${year}-${month}-${day}T${hour}:${minute}`;
  };

  const handleStart = () => {
    const date = new Date();
    const [hour, minute] = date.toTimeString().split(" ")[0].split(":");
    const [year, month, day] = date.toLocaleString().split(". ");
    console.log("분", minute);
    const startTime = getCurrentTime();

    navigator.geolocation.getCurrentPosition(
      (position) => {
        axios
          .get(`/api/work/start/location/${user.teamId}`)
          .then((response) => {
            const { latitude, longitude } = response.data;
            const isValid = validateLocation(
              position.coords.latitude,
              position.coords.longitude,
              longitude,
              latitude
            );

            if (!isValid) {
              alert("위치에서 벗어났습니다.");
              return;
            }

            axios
              .post(`/api/work/start/${user.id}`, {
                work_start_time: startTime,
              })
              .then((response) => {
                setIsWorking(true);
                alert("근무를 시작합니다.");
              })
              .catch((err) => console.error(err.message));
          });
      },
      (error) => {
        console.error(error);
      },
      {
        enableHighAccuracy: false,
        maximumAge: 0,
        timeout: Infinity,
      }
    );
  };

  const handleEnd = () => {
    axios
      .post(`/api/work/end/${user.id}`, {
        work_end_time: getCurrentTime(),
      })
      .then((response) => {
        setIsWorking(false);
        alert("근무를 종료합니다.");
      })
      .catch((err) => console.error(err.message));
  };
  useEffect(() => {
    if (!user.id) {
      const cache = JSON.parse(localStorage.getItem("user"));
      if (!cache) navigate("/login");
      setUser(cache);
    }

    axios.get(`/api/member/myteam/${user.id}`).then((response) => {
      const teamId = response.data;
      setUser({ ...user, teamId: teamId });
    });
  }, []);
  return (
    <>
      <NavBar />
      <div>메인 페이지</div>
      <button onClick={handleStart} disabled={isWorking}>
        Start
      </button>
      <button onClick={handleEnd}>End</button>
      <h1>{user.name}</h1>
      <h1>{user.admin}</h1>
      <WorkChart />
    </>
  );
};

export default Main;