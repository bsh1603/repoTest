import { atom } from "recoil";

export const userState = atom({
  key: "userState", //변수명
  default: {
    id: undefined,
    name: undefined,
    admin: undefined,
  },
});

export const memberState = atom({
  key: "memberState", // 변수명
  default: [],
});

export const teamState = atom({
  key: "teamState", // 변수명
  default: [],
});
