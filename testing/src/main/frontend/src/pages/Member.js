import NavBar from "../components/NavBar";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import styled from "styled-components";
import { loginInfo } from '../App.js';
import {useAtom} from 'jotai';
import { useEffect } from 'react';

const Member = () => {
  const [user,setUser ] = useAtom(loginInfo);
  useEffect(() => {console.log(user)}, [user])
    if(!user){
      return (
          <div>LOADING</div>
      );
    }

  return (
    <>
      <NavBar />
      <div>팀원 조회페이지</div>
    </>
  );
};

export default Member;
