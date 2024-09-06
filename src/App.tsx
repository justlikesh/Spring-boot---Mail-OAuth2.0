import React, { ChangeEvent, useState } from "react";
import "./App.css";
import InputBox from "components/inputBox";

function App() {
  const [id, setId] = useState<string>("");

  const onIdChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    setId(value);
  };

  const onIdButtonClickHandler = () => {};

  return (
    <>
      <InputBox
        title="아이디"
        placeholder="아이디를 입력해주세요"
        type="text"
        value={id}
        onChange={onIdChangeHandler}
        buttonTitle="중복 확인"
        onButtonClick={onIdButtonClickHandler}
        message="사용 가능한 아이디 입니다"
        isErrorMessage={true}
      />
    </>
  );
}

export default App;
