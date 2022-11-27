import NavBar from "../components/NavBar";
import Button from "@mui/material/Button";
import styled from "styled-components";
import Header from "../components/Header";
import { useEffect } from "react";
import axios from "axios";
import { useRecoilValue, useRecoilState } from "recoil";
import { memberState, userState } from "../recoil/atom";

const Work = () => {
  const user = useRecoilValue(userState);
  const [member, setMember] = useRecoilState(memberState);
  const workUser = localStorage.getItem("user");

    useEffect(() => {
      axios.get(`/api/member/my/${user.id}`).then((response) => {
        setMember(response.data);
        console.log(member);
      });
    }, []);

  return (
    <>
      <NavBar />
      <div>근무 조회 페이지</div>

      <div>
        <InputWrapper>
            <h1>{member.id}</h1>
            <h1>{member.team_name}</h1>
            <h1>팀아이디</h1>
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
