import React, { useRef, useState, useEffect } from "react";
import axios from "axios";

function Like({ post, user }) {
  const [userList, setUserList] = useState([]);
  const [like, setLike] = useState(userList.some((u) => u?.id === user?.userId));
  const [showLikeList, setShowLikeList] = useState(false);
  const [likeNum, setLikeNum] = useState(userList?.length);

  useEffect(() => {
    getLikes();
    setLike(userList.some((u) => u?.id === user?.id));
    setLikeNum(userList?.length);
  }, [post, user, userList]);

  const getLikes = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/posts/${post.postId}/likes`
      );
      setUserList(response.data);
      console.log(response.data);
    } catch (error) {
      alert("Couldn't find post likes!");
    }
  };

  const handleLike = async (e) => {
    e.preventDefault();
    const response = await axios.post(
      `http://localhost:8080/posts/${post.postId}/${user.userId}`,
      { headers: { "Content-Type": "application/json" } }
    );
    if (response.status === 200) {
      setLike(true);
    }
  };

  const handleUnlike = async (e) => {
    e.preventDefault();
    const response = await axios.delete(
      `http://localhost:8080/posts/${post.postId}/${user.userId}`,
      { headers: { "Content-Type": "application/json" } }
    );
    if (response.status === 200) {
      setLike(false);
    }
  };

  return (
    <div>
      {like ? (
        <button onClick={handleUnlike}>Unlike</button>
      ) : (
        <button onClick={handleLike}>Like</button>
      )}
      <span>{likeNum} Likes</span>
      {showLikeList ? (
        <>
          <button onClick={() => setShowLikeList(false)}>Hide</button>
          {userList.map((user, i) => (
            <div key={i}>{user.username}</div>
          ))}
        </>
      ) : (
        <button onClick={() => setShowLikeList(true)}>Users</button>
      )}
    </div>
  );
}

export default Like;
