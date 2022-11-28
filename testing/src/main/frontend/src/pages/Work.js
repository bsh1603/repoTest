import NavBar from "../components/NavBar";
import Button from "@mui/material/Button";
import styled from "styled-components";
import Header from "../components/Header";
import { useEffect } from "react";
import axios from "axios";
import { useRecoilValue, useRecoilState } from "recoil";
import { memberState, userState, workState, teamState, stockState } from "../recoil/atom";
import moment from 'moment';
import Table from "../components/WorkTable";

const Work = () => {
  const user = useRecoilValue(userState);
  const [work, setWork] = useRecoilState(workState);
  const [team, setTeam] = useRecoilState(teamState);

    useEffect(() => {
      axios.get(`/api/member/my/${JSON.parse(localStorage.getItem("user")).id}`).then((response) => {
        setWork(response.data);
      });
    }, []);

    useEffect(() => {
      axios.get(`api/member/myteam/${JSON.parse(localStorage.getItem("user")).id}`).then((response) => {
        setTeam(response.data);
      });
    }, []);

    console.log("팀아이디 확인");
    console.log(team);

  return (
    <>
      <NavBar />
      <div>근무 조회 페이지</div>
      <h1>근무테스트</h1>
      <div>
        <InputWrapper>
            <Table rows={work.works} />
        </InputWrapper>
      </div>
    </>
  );
};

export default Work;

const InputWrapper = styled.div`
  max-width: 800px;
  text-align: center;
  margin: 30px auto;
`;
