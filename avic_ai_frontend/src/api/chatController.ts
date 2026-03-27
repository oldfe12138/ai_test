// @ts-ignore
/* eslint-disable */
import request from '@/request'

/** 此处后端没有提供注释 POST /chat/common */
export async function chat(
  body: {
    chatId?: number
    userPrompt?: string
  },
  options?: { [key: string]: any }
) {
  return request<string>('/chat/common', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  })
}

/** 此处后端没有提供注释 POST /chat/streamCommon */
export async function streamChat(
  body: {
    chatId?: number
    userPrompt?: string
  },
  options?: { [key: string]: any }
) {
  return request<string[]>('/chat/streamCommon', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  })
}
