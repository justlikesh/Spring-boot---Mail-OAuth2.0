import React, { useState } from "react";
import "./App.css";
import InputBox from "components/inputBox";

function App() {
  const [id, setId] = useState<string>("");

  const onIdChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    setId(value);
  };

  return (
    <>
      <InputBox
        title="아이디"
        placeholder="아이디를 입력해주세요"
        type="text"
        value={id}
        onChange={onIdChangeHandler}
      />
    </>
  );
}

export default App;
