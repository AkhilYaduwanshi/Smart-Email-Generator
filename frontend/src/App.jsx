import { useState } from 'react'
import axios from 'axios'
import './App.css'

function App() {
  const [message, setMessage] = useState('')
  const [tone, setTone] = useState('Professional')

  const [subject, setSubject] = useState('')
  const [body, setBody] = useState('')

  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')
  const [copied, setCopied] = useState(false)

  const generateEmail = async () => {
    if (!message.trim()) {
      setError('Please enter some email content first.')
      return
    }

    setLoading(true)
    setError('')
    setSubject('')
    setBody('')
    setCopied(false)

    try {
      const response = await axios.post(
          'http://localhost:8081/generate-email',
          {
            message: message,
            tone: tone
          }
      )

      setSubject(response.data.subject)
      setBody(response.data.body)

    } catch (error) {
      console.error('Error generating email:', error)
      setError('Unable to generate email. Please try again.')
    } finally {
      setLoading(false)
    }
  }

  const copyEmail = async () => {
    if (!subject || !body) {
      return
    }

    const email = `Subject: ${subject}\n\n${body}`

    try {
      await navigator.clipboard.writeText(email)

      setCopied(true)

      setTimeout(() => {
        setCopied(false)
      }, 2000)

    } catch (error) {
      console.error('Unable to copy email:', error)
    }
  }

  const clearEmail = () => {
    setMessage('')
    setSubject('')
    setBody('')
    setError('')
    setCopied(false)
  }

  return (
      <div className="app">

        <div className="container">

          <h1 className="title">
            Smart Email Generator
          </h1>

          <p className="subtitle">
            Generate professional emails with the power of AI
          </p>

          {/* Input Card */}
          <div className="form-card">

            <label htmlFor="emailContent">
              Email Content
            </label>

            <textarea
                id="emailContent"
                placeholder="Write what you want to say in the email..."
                value={message}
                onChange={(e) => {
                  setMessage(e.target.value)
                  setError('')
                }}
            />

            <div className="field">

              <label htmlFor="tone">
                Tone
              </label>

              <select
                  id="tone"
                  value={tone}
                  onChange={(e) => setTone(e.target.value)}
              >
                <option value="Professional">
                  Professional
                </option>

                <option value="Friendly">
                  Friendly
                </option>

                <option value="Casual">
                  Casual
                </option>
              </select>

            </div>

            {error && (
                <p className="error-message">
                  {error}
                </p>
            )}

            <button
                className="generate-btn"
                onClick={generateEmail}
                disabled={loading}
            >
              {loading ? 'Generating...' : 'Generate Email'}
            </button>

          </div>

          {/* Result Card */}
          <div className="result-card">

            <div className="result-header">

              <h2 className="result-title">
                Generated Email
              </h2>

              {subject && body && (
                  <button
                      className="clear-btn"
                      onClick={clearEmail}
                  >
                    Clear
                  </button>
              )}

            </div>

            {!subject && !body && !loading && (
                <p className="result-placeholder">
                  Your generated email will appear here.
                </p>
            )}

            {loading && (
                <div className="loading-message">
                  Generating your email...
                </div>
            )}

            {subject && (
                <div className="email-section">

                  <label>
                    Subject
                  </label>

                  <div className="subject-box">
                    {subject}
                  </div>

                </div>
            )}

            {body && (
                <div className="email-section">

                  <label>
                    Email
                  </label>

                  <div className="body-box">
                    {body}
                  </div>

                </div>
            )}

            {subject && body && (
                <div className="result-actions">

                  <button
                      className="copy-btn"
                      onClick={copyEmail}
                  >
                    {copied ? 'Copied!' : 'Copy Email'}
                  </button>

                  <button
                      className="regenerate-btn"
                      onClick={generateEmail}
                      disabled={loading}
                  >
                    Regenerate
                  </button>

                </div>
            )}

          </div>

        </div>

      </div>
  )
}

export default App