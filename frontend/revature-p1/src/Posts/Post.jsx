import React from 'react'

function Post({post}) {
  return (
    <div className='post'>
      <h1>{post.content}</h1>
      <p>Shared by: {post.user.username}</p>
      <p>Shares: {post.shares}</p>
    </div>
  )
}

export default Post