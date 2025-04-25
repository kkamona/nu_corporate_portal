// hooks/useContacts.ts
import { useEffect, useState } from "react";
import { UserType } from "@/types/user/user.type";

export const useContacts = () => {
  const [contacts, setContacts] = useState<UserType[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch("/api/users?page=0&size=50") // adjust page/size if needed
      .then(res => res.json())
      .then(data => {
        setContacts(data.content); // content[] from Swagger response
        setLoading(false);
      });
  }, []);

  return { contacts, loading };
};
