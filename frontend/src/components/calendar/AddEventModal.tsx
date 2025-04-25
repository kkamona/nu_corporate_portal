'use client'

import React, { useState } from 'react';
import { Button } from "@/components/ui/button";  // Import Button component

// Define the Event interface with all the required properties
interface Event {
  id: number;
  title: string;
  description: string;
  startDate: string;
  endDate: string;
  startTime: string;
  endTime: string;
  isPublic: boolean;
  targetRoles?: string[];
  targetSchools?: string[];
  location: string; // Add location here
}

interface AddEventModalProps {
  showModal: boolean;
  setShowModal: (show: boolean) => void;  // Function to control modal visibility
  onSubmit: (eventData: Event) => void;   // Function to handle form submission
}

const AddEventModal: React.FC<AddEventModalProps> = ({ showModal, setShowModal, onSubmit }) => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [startTime, setStartTime] = useState('');
  const [endTime, setEndTime] = useState('');
  const [isPublic, setIsPublic] = useState(true);
  const [location, setLocation] = useState(''); // New state for location

  const handleSubmit = () => {
    const eventData: Event = {
      id: Math.random(),  // Assign a unique id for the event
      title,
      description,
      startDate,
      endDate,
      startTime,
      endTime,
      isPublic,
      location, // Add location here
    };
    onSubmit(eventData);  // Pass data to parent
    setShowModal(false);  // Close the modal after submission
  };

  if (!showModal) return null;  // If modal is not open, don't render it

  return (
    <div className="fixed top-0 left-0 w-full h-full bg-gray-700 bg-opacity-50 flex justify-center items-center">
      <div className="bg-card p-8 rounded-xl shadow-xl w-full max-w-xl">
        {/* Modal Title */}
        <h3 className="text-3xl font-prata text-primary mb-6 text-center">Add Event</h3>

        {/* Title Input */}
        <input
          type="text"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="Event Title"
          className="mb-4 p-4 w-full border-2 border-sidebar-border rounded-lg font-inter text-lg"
        />

        {/* Description Input */}
        <textarea
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          placeholder="Event Description"
          className="mb-4 p-4 w-full border-2 border-sidebar-border rounded-lg font-inter text-lg"
        />

        {/* Date Fields */}
        <div className="flex space-x-4 mb-4">
          <input
            type="date"
            value={startDate}
            onChange={(e) => setStartDate(e.target.value)}
            className="p-4 w-full border-2 border-sidebar-border rounded-lg font-inter text-lg"
          />
          <input
            type="date"
            value={endDate}
            onChange={(e) => setEndDate(e.target.value)}
            className="p-4 w-full border-2 border-sidebar-border rounded-lg font-inter text-lg"
          />
        </div>

        {/* Time Fields */}
        <div className="flex space-x-4 mb-4">
          <input
            type="time"
            value={startTime}
            onChange={(e) => setStartTime(e.target.value)}
            className="p-4 w-full border-2 border-sidebar-border rounded-lg font-inter text-lg"
          />
          <input
            type="time"
            value={endTime}
            onChange={(e) => setEndTime(e.target.value)}
            className="p-4 w-full border-2 border-sidebar-border rounded-lg font-inter text-lg"
          />
        </div>

        {/* Location Input */}
        <input
          type="text"
          value={location}
          onChange={(e) => setLocation(e.target.value)}
          placeholder="Event Location"
          className="mb-4 p-4 w-full border-2 border-sidebar-border rounded-lg font-inter text-lg"
        />

        {/* Public Event Checkbox */}
        <label className="block mb-6 font-inter text-lg">
          <input
            type="checkbox"
            checked={isPublic}
            onChange={() => setIsPublic(!isPublic)}
            className="mr-2"
          />
          Public Event
        </label>

        {/* Buttons */}
        <div className="flex justify-center space-x-4">
          {/* Save Event Button */}
          <Button
            variant="default"
            size="lg"
            onClick={handleSubmit}
          >
            Save Event
          </Button>

          {/* Cancel Button */}
          <Button
            variant="outline"
            size="lg"
            onClick={() => setShowModal(false)}
          >
            Cancel
          </Button>
        </div>
      </div>
    </div>
  );
};

export default AddEventModal;
