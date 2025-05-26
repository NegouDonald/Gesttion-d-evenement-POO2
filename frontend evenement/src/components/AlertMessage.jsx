export default function AlertMessage({ type = "success", message }) {
    const baseStyle = "px-4 py-2 rounded text-sm font-medium mt-2";
    const styles = {
      success: "bg-green-100 text-green-800",
      error: "bg-red-100 text-red-800",
      info: "bg-blue-100 text-blue-800"
    };
  
    return (
      <div className={`${baseStyle} ${styles[type]}`}>
        {message}
      </div>
    );
  }
  