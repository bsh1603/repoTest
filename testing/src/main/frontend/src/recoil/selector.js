import { selector } from "recoil";
import { workState } from "./atom";
import { chartState, userState } from "./atom";
import axios from "axios";
const getWorkTime = (workTime) => {
    const [day, time] = workTime.split('T');
    const [hour, min] = time.split('-');
    const workMin = (Number(hour) - 9) * 60 + Number(min);
    return [day, workMin]
  };

export const objWorkState = selector({
    key: 'objWorkState',
    get: ({get}) => {
      const workArray = get(workState);
      return workArray.reduce((acc,cur) => {
        const [day, workMin] = getWorkTime(cur.work_time);
        return  {...acc, [day] : workMin};
      },{})
     
    },
  });

export const chartDataState = selector({
  key: "chartDataState",
  get: async({ get }) => {
    const user = get(userState);
    const response = await axios.get(`/api/work/chart/${user.id}`);
    const chartState = response.data;
    const labels = [...Object.keys(chartState)];
    labels.sort()
    labels.push("")
    const values = [...labels.map(key => chartState[key]),0];

    const data = {
    labels: labels,
    datasets: [
      {
        label: "날짜",
        data: values,
        fill: true,
        backgroundColor: "rgba(75,192,192,0.2)",
        borderColor: "rgba(75,192,192,1)",
      },
    ],
  }
    return data;

  },
});