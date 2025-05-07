import React, { useEffect, useState } from "react";
import axios from "axios";

type ShareClass = {
  name: string;
  navDate: number[];
};

type Fund = {
  fundType: string;
  assetType: string;
  currency: string;
  fundName: string;
  price: number;
  shareClass: ShareClass[];
};

const FundList: React.FC = () => {
  const [funds, setFunds] = useState<Fund[]>([]);
  const [showModal, setShowModal] = useState(false);
  const [selectedFund, setSelectedFund] = useState<Fund | null>(null);
  const [quantity, setQuantity] = useState<number>(1);

  useEffect(() => {
    const token = localStorage.getItem("token");

    if (!token) {
      alert("No token found. Please login.");
      return;
    }

    axios
      .get<Fund[]>("http://localhost:9000/funds", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        console.log("Funds loaded:", response.data);
        setFunds(response.data);
      })
      .catch((error) => {
        console.error("Failed to load funds:", error);
        alert("Failed to load funds. Please login again.");
      });
  }, []);

  const handleBuyClick = (fund: Fund) => {
    setSelectedFund(fund);
    setShowModal(true);
  };

  const getRandomInt = (min: number, max: number): number => {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  };

  const handleConfirmBuy = () => {
    if (!selectedFund) return;

    const token = localStorage.getItem("token");
    if (!token) {
      alert("You must be logged in to trade.");
      return;
    }

    const tradePayload = {
      tradeId: getRandomInt(100000, 999999), // Random 6-digit trade ID
      fundname: selectedFund.fundName,
      quantity,
      price: selectedFund.price,
      tradeType: "BUY",
    };

    axios
      .post("http://localhost:9000/trades/processTrades", tradePayload, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then(() => {
        alert("Trade placed successfully!");
        setShowModal(false);
        setQuantity(1);
      })
      .catch((error) => {
        console.error("Trade failed:", error);
        alert("Trade failed. Try again.");
      });
  };

  return (
    <>
      {/* ... rest of the JSX remains unchanged ... */}
    </>
  );
};

export default FundList;
