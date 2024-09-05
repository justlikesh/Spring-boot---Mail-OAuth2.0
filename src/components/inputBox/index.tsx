import {
  ChangeEvent,
  forwardRef,
  KeyboardEvent as ReactKeyboardEvent,
} from "react";
import "./style.css";

interface Props {
  title: string;
  placeholder: string;
  type: "text" | "password";
  value: string;
  message?: string;
  isErrorMessage?: boolean;
  buttonTitle?: string;
  onChange: (event: ChangeEvent<HTMLInputElement>) => void;
  onKeyDown?: (event: ReactKeyboardEvent<HTMLInputElement>) => void;
  onButtonClick?: () => void;
}

const InputBox = forwardRef<HTMLInputElement, Props>((props: Props, ref) => {
  const {
    title,
    placeholder,
    type,
    value,
    isErrorMessage,
    buttonTitle,
    message,
    onChange,
    onKeyDown,
    onButtonClick,
  } = props;

  const buttonClass =
    value === "" ? "input-box-button-disable" : "input-box-button";
  const messageClass = isErrorMessage
    ? "input-box-message-error"
    : "input-box-message";

  return (
    <div className="input-box">
      <div className="input-box-title">{"title"}</div>
      <div className="input-box-content">
        <div className="input-box-body">
          <input
            ref={ref}
            className="input-box-input"
            placeholder={placeholder}
            type={type}
            value={value}
            onChange={onChange}
            onKeyDown={onKeyDown}
          />
          {buttonTitle !== undefined && onButtonClick !== undefined && (
            <div className={buttonClass} onClick={onButtonClick}>
              {buttonTitle}
            </div>
          )}
        </div>
        {message !== undefined && <div className={messageClass}>{message}</div>}
      </div>
    </div>
  );
});

export default InputBox;
