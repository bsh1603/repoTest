import NavBar from "../components/NavBar";
import Button from "@mui/material/Button";
import styled from "styled-components";
import Header from "../components/Header";
import { useEffect } from "react";
import axios from "axios";
import { useRecoilValue, useRecoilState } from "recoil";
import { memberState, userState } from "../recoil/atom";
import Table from "../components/Table";

const Member = () => {
  const user = useRecoilValue(userState);
  const [member, setMember] = useRecoilState(memberState);
  const memberUser = JSON.parse(localStorage.getItem("user"));

  useEffect(() => {
    axios.get(`/api/member/${memberUser.id}`).then((response) => {
      setMember(response.data);
      console.log(member);
    });
  }, []);

  return (
    <>
      <Header />
      <NavWrapper>
        <NavBar />
      </NavWrapper>
      <InputWrapper>
      <Table rows={member} />
      <Button variant="contained" fullWidth={true} size="small">
        팀원 정보 삭제
      </Button>
      </InputWrapper>
    </>
  );
};

export default Member;

const InputWrapper = styled.div`
  max-width: 1000px;
  text-align: center;
  margin: 0px auto;
`;

const NavWrapper = styled.div`
  padding-top: 150px;
`;