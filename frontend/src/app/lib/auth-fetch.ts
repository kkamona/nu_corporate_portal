import { getSession } from "next-auth/react";

export const fetchWithAuth = async (path: string, options = {}) => {
  const session = await getSession();

  const headers = {
    ...(options as any).headers,
    Authorization: `Bearer ${session?.user?.accessToken}`,
  };

  const res = await fetch(`${process.env.NEXT_PUBLIC_SERVER_URL}${path}`, {
    ...options,
    headers,
  });

  return res;
};
